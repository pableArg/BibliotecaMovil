package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit

class Book(val kind : String, val id : String, val etag :  String, val selfLink : String, val volumeInfo : VolumeInfo, val saleInfo : SaleInfo,
              val accessInfo : AccessInfo, val searchInfo : SearchInfo)

class AccessInfo(val country : String, val viewability : String, val embeddable : Boolean,
                 val publicDomain : Boolean, val textToSpeechPermission : String, val epub : Epub, val pdf : Pdf,
                 val webReaderLink : String, val accessViewStatus : String, val quoteSharingAllowed : Boolean)

class SaleInfo(val country : String, val saleability : String, val isEbook : Boolean, val buyLink : String)

class SearchInfo(val textSnippet : String)

class VolumeInfo(val title : String, val authors : ArrayList<String>, val publishedDate : String, val industryIdentifiers : ArrayList<Identifier>,
                 val readingModes : Mode, val pageCount : Int, val printType : String, val maturityRating : String, val allowAnonLogging : Boolean, val contentVersion : String,
                 val panelizationSummary : Panel, val imageLinks : ImageLink, val language : String, val previewLink : String, val infoLink : String, val canonicalVolumeLink : String)

class Epub(val isAvailable : Boolean, val downloadLink : String)

class Identifier(val type : String, val identifier : String)

class ImageLink(val smallThumbnail : String, val thumbnail : String)

class Mode(val text : Boolean, val image : Boolean)

class Panel(val containsEpubBubbles : Boolean, val containsImageBubbles : Boolean)

class Pdf(val isAvailable : Boolean, val downloadLink : String)