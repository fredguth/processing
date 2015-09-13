import processing.pdf.*;
boolean record = false;


//int [] colors = {#D64453, #5BA5CB};

String [] interesses = {
	"Azulejos",
	"Contabilidade",
	"Excel",
	"História da Matemática",
	"Samba de Gafieira",
	"SEO",
	"Copywriting",
	"Geometria Grega",
	"Programação Funcional",
	"Rails",
	"Ember.js",
	"Processing",
	"Mitologia Comparada",
	"Piano Popular",
	"Bossa Nova",
	"Produção Cultural",
	"Criptografia",
	"Raduan Nassar",
	"Athos Bulcão",
	"Dieter Rams",
	"Pinxinguinha",
	"Lean Startups",
	"Emprendedorismo",
	"Sistemas Formais",
	"Educação",
	"Home Schooling",
	"Marketing",
	"Publicidade",
	"Modernismo Brasileiro",
	"Arquitetura",
	"Criação com Apego",
	"Paternidade",
	"Erlang",
	"Elixir",
	"Feminismo"


};
void setup(){
	size(1024,768);
	H.init(this).background(#FEEADE);
	smooth();

	for (int j=0;j<10;j++){
		int ccolor=H.BLACK;

		for (int i =0; i < interesses.length; i++){
				HText interesse = new HText(interesses[i]);
				interesse
					.loc(0.2*width+(int) random(0.6*width), 0.2*height+(int) random (0.6*height))
					.anchorAt(H.CENTER)
					.noStroke()
					//.size (15+ (int) random(50) )
					//.stroke(#CCCCCC)
					.fill(ccolor, 80)//alpha 100
				;
				H.add(interesse);

		}
	}
	for (int i =0; i < interesses.length; i++){
			HText interesse = new HText(interesses[i]);
			interesse
				.loc(0.2*width+(int) random(0.6*width), 0.2*height+(int) random (0.6*height))
				.anchorAt(H.CENTER)
				.noStroke()
				//.size (15+ (int) random(50) )
				//.stroke(#CCCCCC)
				.fill(#54458C)//alpha 100
			;
			H.add(interesse);

	}
	HText design = new HText("Design");
	design
				.anchorAt(H.CENTER)
				.fill(#D64453)
				.size(100)
				.loc(width/2-100,height/2-50)
	;
	H.add(design);
//	H.drawStage();

}

void draw() {
	H.drawStage();
	PGraphics tmp = null;

	if (record) {
		tmp = beginRecord(PDF, "render-slide2.pdf");
	}

	if (tmp == null) {
		H.drawStage();
		} else {
			PGraphics g = tmp;
			boolean uses3D = false;
			float alpha = 1;
			H.stage().paintAll(g, uses3D, alpha);
		}

		if (record) {
			endRecord();
			record = false;
		}
	}

	void keyPressed() {
		if (key == 's') {
			record = true;
			draw();
		}
	}
