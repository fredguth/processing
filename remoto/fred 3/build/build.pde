import processing.pdf.*;
import hype.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;


HDrawablePool pool1, pool2;
HPixelColorist colors1;
HColorPool colors2;
HCanvas c1,c2;
boolean record = false;


void setup(){
	size(500,500);
	H.init(this).background(#66ccff);
	c1 = new HCanvas().autoClear(true);
	c2 = new HCanvas().autoClear(true);
	// c3 = new HCanvas().autoClear(true);
	H.add(c1);
	H.add(c2);

	// H.add(c3);


	smooth();
	//
	colors2 = new HColorPool(#FFFFFF, #FFFFFF,#FFFFFF,#FFFFFF,#FFFFFF,#FFFFFF,#0066cc, #003366, #00ccff);

}

void draw(){


	pool1 = new HDrawablePool(100);
	//pool2 = new HDrawablePool(100);
	//pool1.autoParent(c1)
	pool1.autoParent(c1)
		.add(new HShape("angles1.svg"),4)
		.add(new HShape("angles2.svg"),2)
		.add(new HShape("angles3.svg"),1)
		.add(new HShape("angles1m.svg"),4)
		.add(new HShape("angles2m.svg"),2)
		.add(new HShape("angles3m.svg"),1)


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
			.cols(10)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.strokeWeight(0)
						.anchorAt(H.CENTER)
						.size(50,50)

						// .fill(colors.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 180 );

					// if (colors1.getColor(d.x(), d.y()) <= (#666666)) {
					// 	// log(colors1.getColor(d.x(), d.y()));
					// 	d.randomColors(colors2.fillOnly());
					// }
					// else {
					d.randomColors(colors2.fillOnly());
					//d.alpha(0);
					//}

					//d.randomColors(colors1.fillOnly());
				}
			}
		)
	;
	// pool2.autoParent(c2)
	// 	.add(new HShape("testB-1.svg"))
	// 	.add(new HShape("testB-2.svg"))

	// 	// .add(new HShape("svg2.svg"))
	// 	// .add(new HShape("svg3.svg"))
	// 	// .add(new HShape("svg4.svg"))
	// 	// .add(new HShape("svg5.svg"))
	// 	// .add(new HShape("svg6.svg"))

	// 	.layout(
	// 		new HGridLayout()
	// 		.startX(25)
	// 		.startY(25)
	// 		.spacing(50,50)
	// 		.cols(10)
	// 	)

	// 	.onRequest(
	// 		new HCallback() {
	// 			public void run(Object obj) {
	// 				HShape d = (HShape) obj;
	// 				d
	// 					.enableStyle(false)
	// 					.strokeWeight(0)
	// 					.anchorAt(H.CENTER)
	// 					.size(50,50)
	// 					.z(1)

	// 				;
	// 				d.rotate( (int)random(4) * 90 );
	// 				colors1 = new HPixelColorist(c1.graphics()).fillOnly();
	// 				d.fill(colors1.getColor(d.x(), d.y()));
	// 				//d.fill(c1.graphics().getColor(d.x(), d.y()));
	// 				// if (colors1.getColor(d.x(), d.y()) <= (#666666)) {
	// 				// 	// log(colors1.getColor(d.x(), d.y()));
	// 				// 	d.randomColors(colors2.fillOnly());
	// 				// }
	// 				// else {
	// 									//d.alpha(0);
	// 				//}

	// 				//d.randomColors(colors1.fillOnly());
	// 			}
	// 		}
	// 	)
	// ;



	if (mousePressed) {
			//PGraphics tmp = beginRecord(PDF, "frame-####.pdf");
			pool1.shuffleRequestAll();
	 		// pool2.shuffleRequestAll();
	 		//H.stage().paintAll(tmp, false, 1);
		//	endRecord();
	 		H.drawStage();
			pool1.drain(true);
			// pool2.drain(true);

 	}


}

