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
	size(1080,360);
	H.init(this).background(#ffffff);

	smooth();
	colors1 = new HPixelColorist("mancha1080.png").fillOnly();
	//colors2 = new HColorPool(#FFFFFF, #F9BF00, #3DA6AB, #96959C, #D7BC96, #653B96, #F6A39E, #2BBCBF, #EF4A23);
	//colors2 = new HColorPool(#ECECEC,  #D7191B, #1B2B3C, #190A0B);
	colors2 = new HColorPool( #66ccff, #ECECEC, #0066ff, #003366, #ff761a);
}

void draw(){
	pool1 = new HDrawablePool(250);
	pool2 = new HDrawablePool(250);
	pool1.autoAddToStage()
		.add(new HShape("tilman-01.svg"))
		.add(new HShape("tilman-02.svg"))
		.add(new HShape("tilman-03.svg"))
		.add(new HShape("tilman-04.svg"))
		.add(new HShape("tilman-05.svg"))
		.add(new HShape("tilman-06.svg"))
		.add(new HShape("tilman-07.svg"))
		.add(new HShape("tilman-08.svg"))
		.add(new HShape("tilman-09.svg"))
		.add(new HShape("tilman-10.svg"))
		.add(new HShape("tilman-11.svg"))
		.add(new HShape("tilman-12.svg"))
		.add(new HShape("tilman-13.svg"))
		.add(new HShape("tilman-14.svg"))
		.add(new HShape("tilman-15.svg"))
		.add(new HShape("tilman-16.svg"))
		.add(new HShape("tilman-17.svg"))
		.add(new HShape("tilman-18.svg"))
		.add(new HShape("tilman-19.svg"))



		.layout(
			new HGridLayout()
			.startX(0)
			.startY(0)
			.spacing(60,60)
			.cols(18)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.noStroke()
						.anchorAt(H.CENTER)
						.size(60,60)
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
		.add(new HShape("tilman-01.svg"))
		.add(new HShape("tilman-02.svg"))
		.add(new HShape("tilman-03.svg"))
		.add(new HShape("tilman-04.svg"))
		.add(new HShape("tilman-05.svg"))
		.add(new HShape("tilman-06.svg"))
		.add(new HShape("tilman-07.svg"))
		.add(new HShape("tilman-08.svg"))
		.add(new HShape("tilman-09.svg"))
		.add(new HShape("tilman-10.svg"))
		.add(new HShape("tilman-11.svg"))
		.add(new HShape("tilman-12.svg"))
		.add(new HShape("tilman-13.svg"))
		.add(new HShape("tilman-14.svg"))
		.add(new HShape("tilman-15.svg"))
		.add(new HShape("tilman-16.svg"))
		.add(new HShape("tilman-17.svg"))
		.add(new HShape("tilman-18.svg"))
		.add(new HShape("tilman-19.svg"))
		.layout(
			new HGridLayout()
			.startX(0)
			.startY(0)
			.spacing(60,60)
			.cols(18)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.noStroke()
						.anchorAt(H.CENTER)
						.size(60,60)
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

