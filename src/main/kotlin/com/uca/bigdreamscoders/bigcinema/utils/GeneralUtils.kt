package com.uca.bigdreamscoders.bigcinema.utils

import com.uca.bigdreamscoders.bigcinema.domain.Account
import com.uca.bigdreamscoders.bigcinema.services.AccountService
import javax.servlet.http.HttpServletRequest

class GeneralUtils{
    companion object {
        fun returnAccount(request: HttpServletRequest, accountService: AccountService): Account?{
            val accountLoged: String? = request.session.getAttribute("USER_LOGED") as String?
            var accId = accountLoged!!.split(Regex("[=]"),3)[2]
            accId = accId.split(Regex("[,]"))[0].trim()
            val account = accountService.findByOne(accId)
            if(account.isPresent){
                return account.get()
            }
            else{
                return null
            }
        }
    }
}