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
HEllipse d;


public void setup(){
	
	H.init(this).background(0xffcccccc);

	
	colors1 = new HPixelColorist("mancha1080.png").fillOnly();
	//colors2 = new HColorPool(#FFFFFF, #F9BF00, #3DA6AB, #96959C, #D7BC96, #653B96, #F6A39E, #2BBCBF, #EF4A23);
	colors2 = new HColorPool(0xffECECEC,  0xffD7191B, 0xff1B2B3C, 0xff190A0B);
	// c1 = new HCanvas().autoClear(true);
	// c2 = new HCanvas().autoClear(true);
	// H.add(c1);
	// H.add(c2);
	for(int i=0; i<18; i++){
	  for(int j=0; j<6; j++){
		  d = new HEllipse()
		   	.strokeWeight(i*j)
				.stroke(0xffFF3300)
				.fill(0xff111111)
				.size(50,50)
				.loc( i*50+25, j*50+25 )
				.anchorAt(H.CENTER)
			;
			H.add(d);
		}

		H.drawStage();
	}

}


// void draw(){


// 	pool2.autoParent(c2)
// 		.add(new HShape("bola.svg"))

// 		.layout(
// 			new HGridLayout()
// 			.startX(25)
// 			.startY(25)
// 			.spacing(50,50)
// 			.cols(18)
// 		)

// 		.onRequest(
// 			new HCallback() {
// 				public void run(Object obj) {
// 					HShape d = (HShape) obj;
// 					d
// 						.enableStyle(false)
// 						.noStroke()
// 						.anchorAt(H.CENTER)
// 						.size(5,5)
// 						.fill(#FFFFFF)
// 					;

// 				}
// 			}
// 		)
// 	;



// 	if (mousePressed) {
// 			PGraphics tmp = beginRecord(PDF, "frame-####.pdf");
// 			pool1.shuffleRequestAll();
// 	 		pool2.shuffleRequestAll();
// 	 		//H.stage().paintAll(tmp, false, 1);
// 			endRecord();
// 	 		H.drawStage();
// 			pool1.drain(true);
// 			pool2.drain(true);





//   	}

// }

  public void settings() { 	size(900,300); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
