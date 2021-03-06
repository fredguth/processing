import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.pdf.*; 
import hype.*; 
import hype.extended.colorist.*; 
import hype.extended.layout.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class build extends PApplet {






HDrawablePool pool1, pool2, pool3;
HPixelColorist colors1;
HColorPool colors2;
HCanvas c1,c2,c3;
boolean record = false;


public void setup(){
	
	H.init(this).background(0xffffffff);

	
	colors1 = new HPixelColorist("mancha2.png").fillOnly();
	//colors2 = new HColorPool(#FFFFFF, #F9BF00, #3DA6AB, #96959C, #D7BC96, #653B96, #F6A39E, #2BBCBF, #EF4A23);
	colors2 = new HColorPool(0xffECECEC,  0xffD7191B, 0xff1B2B3C, 0xff190A0B);

}

public void draw(){
	pool1 = new HDrawablePool(60);
	pool2 = new HDrawablePool(60);
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
						.noStroke()
						.anchorAt(H.CENTER)
						.size(60,60)
						// .fill(colors.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 90 );
					if (colors1.getColor(d.x(), d.y()) <= (0xff666666)) {
						// log(colors1.getColor(d.x(), d.y()));
						d.randomColors(colors2.fillOnly());
					}
					else {
						d.fill(0xffFFFFFF);
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
						.noStroke()
						.anchorAt(H.CENTER)
						.size(60,60)
						// .fill(colors1.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 90 );
					if (colors1.getColor(d.x(), d.y()) >= (0xff666666) &&
							colors1.getColor(d.x(), d.y()) <= (0xffCCCCCC)	) {
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

  public void settings() { 	size(1080,360); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
