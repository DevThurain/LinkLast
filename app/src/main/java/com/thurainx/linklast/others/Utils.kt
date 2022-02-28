package com.thurainx.linklast.others

object Utils {
     fun getWebsiteLogoLink(link: String) : String{
        val basedLink = "https://www.google.com/s2/favicons?sz=64&domain_url="
        return basedLink+link;
    }

}