package com.foobar.models

import java.net.URL

/**
  *
  * @param url
  * @param title           title of that page
  * @param urlList List of all the URL in that data
  */
case class PageData(
                     url: URL,
                     title: String,
                     urlList:List[URL]
                   )