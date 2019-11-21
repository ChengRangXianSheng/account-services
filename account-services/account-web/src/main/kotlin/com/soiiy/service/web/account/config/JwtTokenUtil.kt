package com.soiiy.service.web.account.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.Exception
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * JWT工具类
 * @Author ChenRang
 * @Date  2019/11/21 19:30
 */
@Component
class JwtTokenUtil {

    val CLAIM_KEY_USERNAME:String="sub"
    val CLAIM_KEY_CREATED:String="created"
    val CLAIM_KEY_ROLES:String="roles"

    @Autowired
    lateinit var jwtConfig: JwtConfig

    /**
     * 从request中获取用户名
     * @Author: ChenRang
     * @Date: 2019/11/21 19:46
     */
    fun getUsernameFromRequest(request:HttpServletRequest):String?{
        var token=request.getHeader(jwtConfig.header)
        token=token.substring(jwtConfig.prefix!!.length)

        return if (token===null) null else getUsernameFromToken(token)
    }

    /**
     * 从token中获取用户名
     * @Author: ChenRang
     * @Date: 2019/11/21 19:51
     */
    fun getUsernameFromToken(token:String):String{
        var username:String?
        try {
            val claims=getClaimsFromToken(token)
            username=claims.subject
        }catch (e:Exception){
            username=null
        }
        return username!!
    }
    /**
     * 从token中获取创建时间
     * @Author: ChenRang
     * @Date: 2019/11/21 20:01
     */
    fun getCreateDateFromToken(token: String):Date{
        var create:Date?

        try {
            val claims=getClaimsFromToken(token)
            create= Date()
        }catch (e:Exception){
            create=null
        }

        return create!!
    }
    /**
     * 从token中获取过期时间
     * @Author: ChenRang
     * @Date: 2019/11/21 20:10
     */
    fun getExpirationDateFromToken(token: String):Date{
        var exception:Date?

        try {
            val claims=getClaimsFromToken(token)
            exception=claims.expiration
        }catch (e:Exception){
            exception=null
        }

        return exception!!
    }
    /**
     * 生成过期时间
     * @Author: ChenRang
     * @Date: 2019/11/21 20:13
     */
//    private fun generateExpirationDate():Date= Date.from(System.currentTimeMillis()+jwtConfig.time)



    private fun getClaimsFromToken(token: String):Claims{
        var claims:Claims?

        try {
            claims=Jwts.parser()
                    .setSigningKey(jwtConfig.secret)
                    .parseClaimsJws(token)
                    .body
        }catch (e:Exception){
            claims=null
        }

        return claims!!
    }
}