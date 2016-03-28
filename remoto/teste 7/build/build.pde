HCanvas c1;
HPixelColorist colors;
HDrawablePool pool;
HTimer timer;
HDrawable drawable;


void setup() {
	size(500,500);
	H.init(this).background(#FFFFFF);
	smooth();

	//colors = new HPixelColorist("mancha4.png").fillOnly();


	// c1.x(-10000);



}



void draw() {

	pool = new HDrawablePool(120);

	pool.autoAddToStage()
		.add(new HShape("athos1.svg"),1)
		.add(new HShape("athos2.svg"),1)
		.add(new HShape("athos3.svg"),6)
		//.add(new HShape("asadelta.svg"))
		// .add(new HShape("svg5.svg"))
		// .add(new HShape("svg6.svg"))

		.layout(
			new HGridLayout()
			.startX(12.5)
			.startY(50)
			.spacing(25,100)
			.cols(20)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(true)
						.anchorAt(H.CENTER)
					;
					d.rotate( (int)random(2) * 180 );

					//if (colors.getColor(d.x(), d.y()) <= (#666666)) {
					// 	// log(colors.getColor(d.x(), d.y()));
					// 	d.fill(#000000);
					// }

				}
			}
		)
	;

 if (mousePressed) {
 		pool.shuffleRequestAll();
 		H.drawStage();
    pool.drain(true);
  }

  //pool.shuffleRequestAll();




}







// HDrawablePool pool;
// HPixelColorist colors;
// HShape d;

// void setup(){
// 	size(500,500);
// 	H.init(this).background(#FFFFFF);
// 	smooth();

// 	colors = new HPixelColorist("mancha4.png").fillOnly();
// 	pool = new HDrawablePool(100);
// 	pool.autoAddToStage()
// 		.add(new HShape("quarto.svg"),4)
// 		.add(new HShape("triangulo.svg"),4)
// 		.add(new HShape("quadrado.svg"),2)
// 		.add(new HShape("asadelta.svg"))
// 		// .add(new HShape("svg5.svg"))
// 		// .add(new HShape("svg6.svg"))

// 		.layout(
// 			new HGridLayout()
// 			.startX(25)
// 			.startY(25)
// 			.spacing(50,50)
// 			.cols(10)
// 		)


// 		.requestAll()
// 	;

// 	H.drawStage();
// }
// void draw() {
//   d.anchorAt(H.CENTER);
//   d.fill(#000000);
//   colorField.applyColor(d);

//   H.drawStage();
// }
// d
// 						.enableStyle(false)
// 						.strokeJoin(ROUND)
// 						.strokeCap(ROUND)
// 						.strokeWeight(0)
// 						.anchorAt(H.CENTER)
// 						.fill(colors.getColor(d.x(), d.y()))
// 					;
// 					d.rotate( (int)random(4) * 90 );