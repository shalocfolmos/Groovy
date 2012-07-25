class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/start"(controller: "person",action: "start")
        "/login"(controller: "person",action: "login")
		"/"(view:"/index")
		"500"(view:'/error')


        "/admin/login"(controller: "person",action: "login")
        "/admin/list"(controller: "Result", action: "adminList")
//        "/admin/list/$person/$page"(controller: "result",action: "adminList")
	}
}
