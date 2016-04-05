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







HDrawablePool pool1, pool2;
HPixelColorist colors1;
HColorPool colors2;
HCanvas c1,c2;
boolean record = false;


public void setup(){
	
	H.init(this).background(0xffffffff);
	// c1 = new HCanvas().autoClear(true);
	// c2 = new HCanvas().autoClear(true);
	// c3 = new HCanvas().autoClear(true);
	// H.add(c1);
	// H.add(c2);

	// H.add(c3);


	
	//
	colors2 = new HColorPool(0xffFFFFFF, 0xffFFFFFF,0xffFFFFFF,0xffFFFFFF,0xffFFFFFF,0xffFFFFFF,0xff0066cc, 0xff003366, 0xff00ccff);

}

public void draw(){


	pool1 = new HDrawablePool(200);
	//pool2 = new HDrawablePool(100);
	//pool1.autoAddToStage()
	pool1.autoAddToStage()
		.add(new HShape("angles1.svg"))
		.add(new HShape("angles2.svg"))
		.add(new HShape("angles3.svg"))
		.add(new HShape("angles4.svg"))
		.add(new HShape("angles5.svg"))
		.add(new HShape("angles6.svg"))
		.add(new HShape("angles7.svg"))
		.add(new HShape("angles8.svg"))
		.add(new HShape("angles9.svg"))
		.add(new HShape("angles10.svg"))
		.add(new HShape("angles11.svg"))
		.add(new HShape("angles12.svg"))


		// .add(new HShape("svg2.svg"))
		// .add(new HShape("svg3.svg"))
		// .add(new HShape("svg4.svg"))
		// .add(new HShape("svg5.svg"))
		// .add(new HShape("svg6.svg"))

		.layout(
			new HGridLayout()
			.startX(60)
			.startY(60)
			.spacing(120,120)
			.cols(9)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(true)
						.strokeWeight(0)
						.anchorAt(H.CENTER)
						.size(120,120)

						// .fill(colors.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 180 );
					d.scale(-1 + (int)random(2) * 2,1);

					// if (colors1.getColor(d.x(), d.y()) <= (#666666)) {
					// 	// log(colors1.getColor(d.x(), d.y()));
					// 	d.randomColors(colors2.fillOnly());
					// }
					// else {
					//d.randomColors(colors2.fillOnly());
					//d.alpha(0);
					//}

					//d.randomColors(colors1.fillOnly());
				}
			}
		)
	;
	// pool2.autoAddToStage()
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
			PGraphics tmp = beginRecord(PDF, "frame-####.pdf");
			pool1.shuffleRequestAll();
	 		//pool2.shuffleRequestAll();
	 		H.stage().paintAll(tmp, false, 1);
			endRecord();
	 		H.drawStage();
			pool1.drain(true);
			//pool2.drain(true);

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
