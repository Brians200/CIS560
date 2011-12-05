class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"homescreen", action:"index")
		"500"(view:'/error')
		
		"/login"(controller:"login", action:"login")
	}
}
