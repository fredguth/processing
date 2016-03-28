import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 
import processing.pdf.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class build extends PApplet {



boolean record = false;


//int [] colors = {#D64453, #5BA5CB};
String [] interesses = {"Azulejos", "Processing"};

public void setup(){
	
	H.init(this).background(0xffD9CCC1);
	
//
	float u = (0.7f*width)/10;//unit

	float diameter =4*u;
	int alpha=60;
	for (int i=0;i<interesses.lenght;i++){
		HText interesse = new HText(interesses[i]);
		interesse
			.loc((int) random (width), (int) random (height))
			// .size(diameter)
			// .anchorAt(H.CENTER)
			// .noStroke()
			// //.stroke(#CCCCCC)
			// .fill(colors[1], alpha)//alpha 100
		;
		H.add(interesse);

	}

	H.drawStage();

}

public void draw() {
	H.drawStage();
	PGraphics tmp = null;

	if (record) {
		tmp = beginRecord(PDF, "render-slide2.pdf");
	}

	if (tmp == null) {
		H.drawStage();
	} else {
		PGraphics g = tmp;
		boolean uses3D = false;
		float alpha = 1;
		H.stage().paintAll(g, uses3D, alpha);
	}

	if (record) {
		endRecord();
		record = false;
	}
}

public void keyPressed() {
	if (key == 's') {
		record = true;
		draw();
	}
}
  public void settings() { 	size(1024,568); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
