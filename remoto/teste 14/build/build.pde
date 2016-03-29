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
	H.init(this).background(#ffffff);

	smooth();
	colors1 = new HPixelColorist("mancha2.png").fillOnly();
	colors2 = new HColorPool(#FFFFFF, #F9BF00, #3DA6AB, #96959C, #D7BC96, #653B96, #F6A39E, #2BBCBF, #EF4A23);


}

void draw(){
	pool1 = new HDrawablePool(50);
	pool2 = new HDrawablePool(50);
	pool1.autoAddToStage()
		.add(new HShape("muda1.svg"),16)
		.add(new HShape("muda2.svg"),16)
		.add(new HShape("muda3.svg"),16)
		.add(new HShape("muda4.svg"),16)
		.add(new HShape("muda5.svg"),16)
		.add(new HShape("muda6.svg"),16)
		.add(new HShape("muda7.svg"),1)
		.add(new HShape("muda8.svg"),1)
		.add(new HShape("muda9.svg"),1)

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
						.noStroke()
						.anchorAt(H.CENTER)
						.size(100,100)
						// .fill(colors.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 90 );
					if (colors1.getColor(d.x(), d.y()) <= (#666666)) {
						// log(colors1.getColor(d.x(), d.y()));
						d.randomColors(colors2.fillOnly());
					}
					else {
						d.fill(#FFFFFF);
						d.alpha(0);
					}

					//d.randomColors(colors1.fillOnly());
				}
			}
		)
	;

	pool2.autoAddToStage()
		.add(new HShape("muda1.svg"),1)
		.add(new HShape("muda2.svg"),1)
		.add(new HShape("muda3.svg"),1)
		.add(new HShape("muda4.svg"),1)
		.add(new HShape("muda5.svg"),1)
		.add(new HShape("muda6.svg"),1)
		.add(new HShape("muda7.svg"),16)
		.add(new HShape("muda8.svg"),16)
		.add(new HShape("muda9.svg"),16)

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
						.noStroke()
						.anchorAt(H.CENTER)
						.size(100,100)
						// .fill(colors1.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 90 );
					if (colors1.getColor(d.x(), d.y()) >= (#666666) &&
							colors1.getColor(d.x(), d.y()) <= (#CCCCCC)	) {
						// log(colors1.getColor(d.x(), d.y()));
						d.randomColors(colors2.fillOnly());
					}
					else {
						d.alpha(0);
					}

					//d.randomColors(colors1.fillOnly());
				}
			}
		)
	;



	if (mousePressed) {
			PGraphics tmp = beginRecord(PDF, "frame-####.pdf");
			pool1.shuffleRequestAll();
	 		pool2.shuffleRequestAll();
	 		H.stage().paintAll(tmp, false, 1);
			endRecord();
	 		H.drawStage();
			pool1.drain(true);
			pool2.drain(true);





  	}

}

