class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

//        "/start"(controller: "person",action: "start")
        "/login"(controller: "person",action: "login")
		"/"(controller: "person",action: "start")
		"500"(view:'/error')


        "/admin/login"(controller: "admin",action: "login")
//        "/admin/list/$person/$page"(controller: "result",action: "adminList")
	}
}
