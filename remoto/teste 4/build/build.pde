HDrawablePool pool;
HPixelColorist colors;


void setup(){
	size(1080,360);
	H.init(this).background(#FFFFFF);
	smooth();
	//colors = new HPixelColorist("mancha4.png").fillOnly();

}

void draw(){
	pool = new HDrawablePool(108);
	pool.autoAddToStage()
		.add(new HShape("quarto.svg"),16)
		.add(new HShape("triangulo.svg"),16)
		.add(new HShape("quadrado.svg"),8)
		.add(new HShape("asadelta.svg"),4)
		.add(new HShape("bola.svg"))
		// .add(new HShape("svg2.svg"))
		// .add(new HShape("svg3.svg"))
		// .add(new HShape("svg4.svg"))
		// .add(new HShape("svg5.svg"))
		// .add(new HShape("svg6.svg"))

		.layout(
			new HGridLayout()
			.startX(30)
			.startY(30)
			.spacing(60,60)
			.cols(18)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.strokeJoin(ROUND)
						.strokeCap(ROUND)
						.strokeWeight(0)
						//.fill(colors.getColor( d.x(), d.y()))
						.fill(#000000)
						.anchorAt(H.CENTER)
						.size(58,58)
					;
					d.rotate( (int)random(4) * 90 );
					//d.randomColors(colors.fillOnly());
				}
			}
		)
	;


	if (mousePressed) {
 		pool.shuffleRequestAll();
 		H.drawStage();
    pool.drain(true);
  }
}

// HDrawablePool pool;
// HPixelColorist colors;

// void setup(){
// 	size(500,500);
// 	H.init(this).background(#FFFFFF);
// 	smooth();

// 	colors = new HPixelColorist("mancha.png").fillOnly();
// 	pool = new HDrawablePool(100);
// 	pool.autoAddToStage()
// 		.add(new HShape("quarto.svg"))
// 		// .add(new HShape("svg2.svg"))
// 		// .add(new HShape("svg3.svg"))
// 		// .add(new HShape("svg4.svg"))
// 		// .add(new HShape("svg5.svg"))
// 		// .add(new HShape("svg6.svg"))

// 		.layout(
// 			new HGridLayout()
// 			.startX(25)
// 			.startY(25)
// 			.spacing(50,50)
// 			.cols(10)
// 		)

// 		.onCreate(
// 			new HCallback() {
// 				public void run(Object obj) {
// 					HShape d = (HShape) obj;
// 					d
// 						.enableStyle(false)
// 						.strokeJoin(ROUND)
// 						.strokeCap(ROUND)
// 						.strokeWeight(0)
// 						.fill(colors.getColor( d.x(), d.y()))
// 						.anchorAt(H.CENTER)
// 					;
// 					d.rotate( (int)random(4) * 90 );

// 					// if (colors.getColor(d.x(), d.y()) <= 20) {
// 					// 	d.fill(#000000);
// 					// }


// 					//d.randomColors(colors.fillOnly());
// 				}
// 			}
// 		)
// 		.requestAll()
// 	;

// 	H.drawStage();
// }

