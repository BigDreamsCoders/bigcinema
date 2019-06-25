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
import org.springframework.web.bind.annotation.PostMapping


@Controller
class AccountController{
    @Autowired
    lateinit var accountService: AccountService

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
                return "dashboard"
            } else if(!model.containsAttribute("errorLogin")){
                model.addAttribute("errorLogin", "Login failed check your credencials")
            }
            return "index"
        }
    }

    @GetMapping("/register")
    fun register(account: Account,model: Model): String{
        model.addAttribute("account", account)
        return "register"
    }

    @PostMapping("/signin")
    fun signIn (@Valid account: Account, result:BindingResult,
                model: Model):String{
        model.addAttribute("account", account)
        if(result.hasErrors()){
            return "register"
        }
        accountService.checkUser(account.username).ifPresent {
            model.addAttribute("errorRegister", "Username already in use")
        }
        if(model.containsAttribute("errorRegister")){
            return "register"
        }
        else{
            account.accCor = null
            accountService.save(account)
            model.addAttribute("created", "User created")
            model.addAttribute("loginForm", LoginForm())
            return "index"

        }
    }

}