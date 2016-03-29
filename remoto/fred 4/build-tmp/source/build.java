import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.pdf.*; 
import hype.*; 

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


public void setup(){
	
	H.init(this).background(0xff66ccff);
	c1 = new HCanvas().autoClear(true);
	c2 = new HCanvas().autoClear(true);
	c3 = new HCanvas().autoClear(true);

	H.add(c1);
	H.add(c2);
	H.add(c3);
	
	colors1 = new HPixelColorist("mancha2.png").fillOnly();
	colors2 = new HColorPool(0xffFFFFFF, 0xff0066ff, 0xff003366);
}

public void draw(){
	pool1 = new HDrawablePool(100);
	pool2 = new HDrawablePool(100);
	pool3 = new HDrawablePool(100);
	pool1.autoParent(c1)
		.add(new HShape("testB-1.svg"))
		.add(new HShape("testB-2.svg"))

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

	pool2.autoParent(c2)
		.add(new HShape("testB-1.svg"))
		.add(new HShape("testB-2.svg"))


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
					if (colors1.getColor(d.x(), d.y()) >= (0xff666666) &&
							colors1.getColor(d.x(), d.y()) <= (0xffCCCCCC)	) {
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


	if (mousePressed) {
 		pool1.shuffleRequestAll();
 		pool2.shuffleRequestAll();
 		//pool3.shuffleRequestAll();
 		H.drawStage();
    pool1.drain(true);
    pool2.drain(true);
    //pool3.drain(true);
  }


}

public void saveVector() {
	PGraphics tmp = beginRecord(PDF, "render.pdf");

	H.stage().paintAll(tmp, false, 1); // PGraphics, uses3D, alpha


	endRecord();
}
public void keyPressed() {
	if (key == 's') {
		saveVector();
	}
}
  public void settings() { 	size(500,500); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
