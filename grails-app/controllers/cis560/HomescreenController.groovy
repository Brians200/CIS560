package cis560

class HomescreenController {

    def index = { 
		
		render("""Hello ${chainModel.userName}""")
	}
}
