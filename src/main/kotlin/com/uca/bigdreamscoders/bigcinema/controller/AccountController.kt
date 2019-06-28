package com.uca.bigdreamscoders.bigcinema.controller

import com.uca.bigdreamscoders.bigcinema.form.LoginForm
import com.uca.bigdreamscoders.bigcinema.services.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import javax.validation.Valid

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import com.uca.bigdreamscoders.bigcinema.domain.Account
import com.uca.bigdreamscoders.bigcinema.domain.Record
import com.uca.bigdreamscoders.bigcinema.form.AdminRegisterForm
import com.uca.bigdreamscoders.bigcinema.form.ReasonForm
import com.uca.bigdreamscoders.bigcinema.form.RegisterForm
import com.uca.bigdreamscoders.bigcinema.services.ProvinceService
import com.uca.bigdreamscoders.bigcinema.services.RecordService
import com.uca.bigdreamscoders.bigcinema.services.StateService
import com.uca.bigdreamscoders.bigcinema.utils.GeneralUtils
import org.springframework.web.bind.annotation.PostMapping



@Controller
class AccountController {
    @Autowired
    lateinit var accountService: AccountService
    @Autowired
    lateinit var stateService: StateService
    @Autowired
    lateinit var provinceService: ProvinceService
    @Autowired
    lateinit var recordService: RecordService

    @GetMapping("/")
    fun index(session: HttpSession, model: Model): String {
        val accountLoged: String? = session.getAttribute("USER_LOGED") as String?
        if (accountLoged == null) {
            model.addAttribute("loginForm", LoginForm())
            return "index"
        }
        return "redirect:/dashboard-account"
    }

    @PostMapping("/login")
    fun login(@Valid loginForm: LoginForm, result: BindingResult,
              request: HttpServletRequest, model: Model): String {
        val accountLoged: String? = request.session.getAttribute("USER_LOGED") as String?
        if (accountLoged == null) {
            request.session.setAttribute("USER_LOGED", accountLoged)
        }
        model.addAttribute("loginForm", loginForm)
        if (result.hasErrors()) {
            loginForm.password = ""
            return "index"
        } else {
            var x = 0
            val searchedAcc=accountService.login(loginForm.username, loginForm.password)
            searchedAcc.ifPresent {
                if (!it.accActive) {
                    model.addAttribute("errorLogin", it.inacReason)
                } else {
                    when (!it.actSession) {
                        true -> {
                            request.session.setAttribute("USER_LOGED", it.toString())
                            it.actSession = true
                            accountService.save(it)
                            x = when (it.accRole) {
                                1 -> { 1 }
                                2 -> { 2 }
                                else -> { 2 }
                            }
                        }
                        else -> {
                            model.addAttribute("errorLogin", "Can't log in. Session already active")
                        }
                    }
                }
            }
            if (!searchedAcc.isPresent){
                model.addAttribute("errorLogin", "Account not found. Check credencials")
            }
            if (model.containsAttribute("errorLogin")) {
                return "index"
            }
            return when (x) {
                1 -> {
                    "redirect:/dashboard-account"
                }
                else -> {
                    "redirect:/dashboard-client"
                }
            }
        }
    }

    @GetMapping("/register")
    fun register(registerForm: RegisterForm, model: Model): String {
        model.addAttribute("registerForm", registerForm)
        model.addAttribute("states", stateService.findAll())
        model.addAttribute("provinces", provinceService.findAll())
        return "register"
    }

    @PostMapping("/signin")
    fun signIn(@Valid registerForm: RegisterForm, result: BindingResult,
               model: Model): String {
        model.addAttribute("account", registerForm)
        model.addAttribute("states", stateService.findAll())
        model.addAttribute("provinces", provinceService.findAll())
        if (result.hasErrors()) {
            return "register"
        }
        val account = accountService.checkUser(registerForm.username)
        account.ifPresent {
            model.addAttribute("errorRegister", "Username already in use")
        }
        if (model.containsAttribute("errorRegister")) {
            return "register"
        } else {
            val newAccount = Account()
            newAccount.newAccount(registerForm)
            provinceService.findByProId(registerForm.proId).ifPresent {
                newAccount.province = it
                model.addAttribute("created", "Account created")
            }
            accountService.save(newAccount)

            model.addAttribute("loginForm", LoginForm())
            return "index"
        }
    }

    @PostMapping("/acccount/active")
    fun active(reasonForm: ReasonForm, reason: String, model: Model,
               request: HttpServletRequest): String {
        val admin = GeneralUtils.returnAccount(request, accountService)
        accountService.findByOne(reasonForm.Id).ifPresent {
            it.accActive = !it.accActive
            it.inacReason = reason
            val returned = accountService.save(it)
            recordService.newRecord(returned.accId, "UPDATE ACCOUNT", it.accActive, admin!!)
            model.addAttribute("message", "Account modified")
        }
        if (!model.containsAttribute("message")) {
            model.addAttribute("error", "Problem trying to update")
        }
        model.addAttribute("accounts", accountService.findAll().toList())
        model.addAttribute("reasonForm", reasonForm)
        return "dashboard-account"
    }

    @GetMapping("/account/prepare")
    fun prepareCreateAccount(model: Model): String {
        model.addAttribute("adminRegisterForm", AdminRegisterForm())
        model.addAttribute("states", stateService.findAll())
        model.addAttribute("provinces", provinceService.findAll())
        return "create-account"
    }

    @PostMapping("/account/admin/create")
    fun accountCreateAccount(@Valid adminRegisterForm: AdminRegisterForm, result: BindingResult,
                             model: Model, request: HttpServletRequest): String {
        val admin = GeneralUtils.returnAccount(request, accountService)

        model.addAttribute("account", adminRegisterForm)
        model.addAttribute("states", stateService.findAll())
        model.addAttribute("provinces", provinceService.findAll())
        if (result.hasErrors()) {
            return "create-account"
        }
        val account = accountService.checkUser(adminRegisterForm.username)
        account.ifPresent {
            model.addAttribute("error", "Username already in use")
        }
        if (model.containsAttribute("error")) {
            return "create-account"
        } else {
            val newAccount = Account()
            newAccount.newAdminAccount(adminRegisterForm)
            provinceService.findByProId(adminRegisterForm.proId).ifPresent {
                newAccount.province = it
                model.addAttribute("message", "Account created")
            }
            val returned = accountService.save(newAccount)
            recordService.newRecord(returned.accId, "CREATE ACCOUNT", false, admin!!)
            if (!model.containsAttribute("message")) {
                model.addAttribute("error", "Error creating the new account")
            }
            model.addAttribute("accounts", accountService.findAll().toList())
            model.addAttribute("reasonForm", ReasonForm())
            return "dashboard-account"
        }
    }
}

