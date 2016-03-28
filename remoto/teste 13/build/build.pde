import processing.pdf.*;
import hype.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;


HDrawablePool pool1, pool2, pool3;
HPixelColorist colors1;
HColorPool colors2;
HCanvas c1,c2,c3;
boolean record = false;


void setup(){
	size(500,500);
	H.init(this).background(#66ccff);
	// c1 = new HCanvas().autoClear(true);
	// c2 = new HCanvas().autoClear(true);
	// c3 = new HCanvas().autoClear(true);

	// H.add(c1);
	// H.add(c2);
	// H.add(c3);


	smooth();
	//colors1 = new HPixelColorist("mancha2.png").fillOnly();
	colors2 = new HColorPool(#FFFFFF, #0066ff, #003366);

}

void draw(){

	pool1 = new HDrawablePool(25);
	pool2 = new HDrawablePool(25);
	//pool1.autoParent(c1)
	pool1.autoAddToStage()
		.add(new HShape("01.svg"))
		.add(new HShape("02.svg"))
		.add(new HShape("03.svg"))
		.add(new HShape("04.svg"))
		.add(new HShape("05.svg"))
		.add(new HShape("06.svg"))
		.add(new HShape("07.svg"))
		.add(new HShape("08.svg"))

		.layout(
			new HGridLayout()
			.startX(50)
			.startY(50)
			.spacing(100,100)
			.cols(5)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.strokeWeight(0)
						.anchorAt(H.CENTER)
						.size(100,100)
						// .fill(colors.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 90 );
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

	pool2.autoAddToStage()
		.add(new HShape("01.svg"))
		.add(new HShape("02.svg"))
		.add(new HShape("03.svg"))
		.add(new HShape("04.svg"))
		.add(new HShape("05.svg"))
		.add(new HShape("06.svg"))
		.add(new HShape("07.svg"))
		.add(new HShape("08.svg"))


		// .add(new HShape("svg2.svg"))
		// .add(new HShape("svg3.svg"))
		// .add(new HShape("svg4.svg"))
		// .add(new HShape("svg5.svg"))
		// .add(new HShape("svg6.svg"))

		.layout(
			new HGridLayout()
			.startX(50)
			.startY(50)
			.spacing(100,100)
			.cols(5)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.strokeWeight(0)
						.anchorAt(H.CENTER)
						.size(100,100)
						// .fill(colors1.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 90 );
					// if (colors1.getColor(d.x(), d.y()) >= (#666666) &&
					// 		colors1.getColor(d.x(), d.y()) <= (#CCCCCC)	) {
						// log(colors1.getColor(d.x(), d.y()));
					d.randomColors(colors2.fillOnly());

					// }
					// else {
					// 	d.fill(#FFFFFF);
					// 	d.alpha(0);
					// }

					//d.randomColors(colors1.fillOnly());
				}
			}
		)
	;

	if (record) {
		PGraphics tmp = beginRecord(PDF, "frame-####.pdf");
		H.stage().paintAll(tmp, false, 1);
		endRecord();
		record = false;
		pool1.drain(true);
		pool2.drain(true);
	}
	else{
		H.drawStage();
		pool1.drain(true);
		pool2.drain(true);

	}

	if (mousePressed) {
 		pool1.shuffleRequestAll();
 		pool2.shuffleRequestAll();
  }


}


public void keyPressed() {
	if (key == 's') {
		record = true;
	}
}
