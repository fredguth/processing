import processing.pdf.*;
import hype.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
HDrawablePool pool;
HPixelColorist colors1;
HColorPool colors2, colors3;


void setup(){
	size(900,300);
	H.init(this).background(#FFFFFF);
	smooth();
	colors1 = new HPixelColorist("mancha1080.png").fillOnly();
	//colors2 = new HColorPool(#FFFFFF, #F9BF00, #3DA6AB, #96959C, #D7BC96, #653B96, #F6A39E, #2BBCBF, #EF4A23);
	colors2 = new HColorPool(#D7191B, #1B2B3C, #190A0B, #1B2B3C, #190A0B, #1B2B3C, #190A0B);
	colors3 = new HColorPool(#ECECEC, #ECECEC,#ECECEC, #D7191B, #D7191B,#1B2B3C, #190A0B);
	//colors2 = new HColorPool( #FFFFFF,  #ECECEC,#0066ff, #003366, #0066ff, #003366, #0066ff, #003366);
	//colors3 = new HColorPool( #FFFFFF,  #ECECEC,#0066ff, #003366, #FFFFFF,  #ECECEC,#FFFFFF,  #ECECEC);
}

void draw(){
	pool = new HDrawablePool(120);
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
			.startX(25)
			.startY(25)
			.spacing(50,50)
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
						.anchorAt(H.CENTER)
					;
					d.rotate( (int)random(4) * 90 );
					if (colors1.getColor( d.x(), d.y())< #AAAAAA) {

						d.randomColors(colors2.fillOnly());
					}
					else {
						d.randomColors(colors3.fillOnly());
					}
				}
			}
		)
	;


	if (mousePressed) {
			PGraphics tmp = beginRecord(PDF, "frame-####.pdf");
			pool.shuffleRequestAll();

	 		H.stage().paintAll(tmp, false, 1);
			endRecord();
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

