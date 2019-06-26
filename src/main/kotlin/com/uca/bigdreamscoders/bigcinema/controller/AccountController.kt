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
import com.uca.bigdreamscoders.bigcinema.form.AdminRegisterForm
import com.uca.bigdreamscoders.bigcinema.form.ReasonForm
import com.uca.bigdreamscoders.bigcinema.form.RegisterForm
import com.uca.bigdreamscoders.bigcinema.services.ProvinceService
import com.uca.bigdreamscoders.bigcinema.services.StateService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam


@Controller
class AccountController{
    @Autowired
    lateinit var accountService: AccountService
    @Autowired
    lateinit var stateService: StateService
    @Autowired
    lateinit var provinceService: ProvinceService

    @GetMapping("/")
    fun index(model: Model): String{
        model.addAttribute("loginForm", LoginForm())
        return "index"
    }

    @PostMapping("/login")
    fun login(@Valid loginForm: LoginForm, result:BindingResult,
              model:Model):String{
        model.addAttribute("loginForm", loginForm)
        if(result.hasErrors()){
            loginForm.password = ""
            return "index"
        }
        else{
            accountService.login(loginForm.username, loginForm.password).ifPresent {
                print(it.toString())
                if(!it.accActive){
                    it.accActive = true
                    accountService.save(it)
                    model.addAttribute("login", "Welcome")
                }
                else {
                    model.addAttribute("errorLogin", "Can't log in. Session already active")
                }
            }
            if(model.containsAttribute("login")){
                return "redirect:/dashboard"
            } else if(!model.containsAttribute("errorLogin")){
                model.addAttribute("errorLogin", "Login failed check your credencials")
            }
            return "index"
        }
    }

    @GetMapping("/register")
    fun register(registerForm: RegisterForm, model: Model): String{
        model.addAttribute("registerForm", registerForm)
        model.addAttribute("states", stateService.findAll())
        model.addAttribute("provinces", provinceService.findAll())
        return "register"
    }

    @PostMapping("/signin")
    fun signIn (@Valid registerForm: RegisterForm,result:BindingResult,
                model: Model):String{
        model.addAttribute("account", registerForm)
        model.addAttribute("states", stateService.findAll())
        model.addAttribute("provinces", provinceService.findAll())
        if(result.hasErrors()){
            return "register"
        }
        val account = accountService.checkUser(registerForm.username)
        account.ifPresent {
            model.addAttribute("errorRegister", "Username already in use")
        }
        if(model.containsAttribute("errorRegister")){
            return "register"
        }
        else{
            val newAccount = Account()
            newAccount.newAccount(registerForm)
             provinceService.findByProId(registerForm.proId).ifPresent {
                 newAccount.province = it
                 model.addAttribute("created", "User created")
            }
            accountService.save(newAccount)
            model.addAttribute("loginForm", LoginForm())
            return "index"
        }
    }

    @PostMapping("/acccount/active")
    fun active(reasonForm:ReasonForm,reason: String, model: Model): String {
        print(reasonForm)
        accountService.findByOne(reasonForm.accId).ifPresent {
            it.accActive = !it.accActive
            it.inacReason = reason
            accountService.save(it)
            model.addAttribute("message", "Account modified")
        }
        if (!model.containsAttribute("message")) {
            model.addAttribute("error", "Problem trying to update")
        }
        model.addAttribute("accounts", accountService.findAll().toList())
        model.addAttribute("reasonForm", reasonForm)
        return "dashboard"
    }
    @GetMapping("/account/create")
    fun prepareCreate(model: Model): String{
        model.addAttribute("adminRegisterForm", AdminRegisterForm())
        model.addAttribute("states", stateService.findAll())
        model.addAttribute("provinces", provinceService.findAll())
        return "create-account"
    }

    @PostMapping("/account/create")
    fun accountCreate(@Valid adminRegisterForm: AdminRegisterForm,result:BindingResult,
                      model: Model):String{
        model.addAttribute("account", adminRegisterForm)
        model.addAttribute("states", stateService.findAll())
        model.addAttribute("provinces", provinceService.findAll())
        if(result.hasErrors()){
            return "create-account"
        }
        val account = accountService.checkUser(adminRegisterForm.username)
        account.ifPresent {
            model.addAttribute("error", "Username already in use")
        }
        if(model.containsAttribute("error")){
            return "create-account"
        }
        else{
            val newAccount = Account()
            newAccount.newAdminAccount(adminRegisterForm)
            provinceService.findByProId(adminRegisterForm.proId).ifPresent {
                newAccount.province = it
                model.addAttribute("message", "Account created")
            }
            accountService.save(newAccount)
            if(!model.containsAttribute("message")){
                model.addAttribute("error", "Error creating the new account")
            }
            model.addAttribute("loginForm", AdminRegisterForm())
            return "create-account"
        }
    }
}