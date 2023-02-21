package com.fudie.data.remote.utils

val countryFlags = listOf(
    CountryFlags("American", "https://cdn.pixabay.com/photo/2017/01/25/10/04/usa-2007460_1280.jpg"),
    CountryFlags("British", "https://cdn.pixabay.com/photo/2012/04/11/15/31/united-28519_1280.png"),
    CountryFlags("Canadian", "https://cdn.pixabay.com/photo/2013/07/13/12/18/canada-159585_1280.png"),
    CountryFlags("Chinese", "https://cdn.pixabay.com/photo/2012/04/10/23/05/china-26841_1280.png"),
    CountryFlags("Croatian", "https://cdn.pixabay.com/photo/2016/01/23/22/04/flag-of-croatia-1158161_1280.png"),
    CountryFlags("Dutch", "https://cdn.pixabay.com/photo/2016/11/20/03/23/dutch-1841912_1280.png"),
    CountryFlags("Egyptian", "https://cdn.pixabay.com/photo/2012/04/10/23/13/egypt-26909_1280.png"),
    CountryFlags("French", "https://cdn.pixabay.com/photo/2012/04/11/15/19/france-28463_1280.png"),
    CountryFlags("Greek", "https://cdn.pixabay.com/photo/2012/04/10/23/09/greece-26871_1280.png"),
    CountryFlags("Indian", "https://cdn.pixabay.com/photo/2012/04/10/23/03/india-26828_1280.png"),
    CountryFlags("Irish", "https://cdn.pixabay.com/photo/2018/01/28/18/00/ireland-3114291_1280.png"),
    CountryFlags("Italian", "https://cdn.pixabay.com/photo/2012/04/11/15/35/flag-28543_1280.png"),
    CountryFlags("Jamaican", "https://cdn.pixabay.com/photo/2012/04/14/16/53/flag-34591_1280.png"),
    CountryFlags("Japanese", "https://cdn.pixabay.com/photo/2017/08/29/22/06/japan-2695047_1280.png"),
    CountryFlags("Kenyan", "https://cdn.pixabay.com/photo/2021/09/22/19/15/kenya-6647747_1280.jpg"),
    CountryFlags("Malaysian", "https://cdn.pixabay.com/photo/2021/11/15/09/57/malaysia-6797563_1280.png"),
    CountryFlags("Mexican", "https://cdn.pixabay.com/photo/2012/04/26/11/55/flag-42281_1280.png"),
    CountryFlags("Moroccan", "https://cdn.pixabay.com/photo/2017/09/09/15/59/flag-2732465_1280.jpg"),
    CountryFlags("Polish", "https://cdn.pixabay.com/photo/2019/11/03/10/55/poland-4598361_1280.jpg"),
    CountryFlags("Portuguese", "https://cdn.pixabay.com/photo/2012/04/10/23/11/portugal-26886_1280.png"),
    CountryFlags("Russian", "https://cdn.pixabay.com/photo/2012/04/10/23/12/russia-26896_1280.png"),
    CountryFlags("Spanish", "https://cdn.pixabay.com/photo/2012/04/11/15/33/spain-28530_1280.png"),
    CountryFlags("Thai", "https://cdn.pixabay.com/photo/2021/11/16/02/35/thailand-6799924_1280.png"),
    CountryFlags("Tunisian", "https://cdn.pixabay.com/photo/2013/07/13/14/17/tunisia-162444_1280.png"),
    CountryFlags("Turkish", "https://cdn.pixabay.com/photo/2012/04/10/23/02/turkey-26820_1280.png"),
    CountryFlags("Unknown", "https://cdn.pixabay.com/photo/2012/04/23/16/22/flag-38806_1280.png"),
    CountryFlags("Vietnamese", "https://cdn.pixabay.com/photo/2012/04/10/23/04/vietnam-26834_1280.png"),
)

data class CountryFlags(
    val name: String,
    val flagUrl: String
)