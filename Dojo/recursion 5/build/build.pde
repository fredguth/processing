
void setup(){
	size(600,600);
	H.init(this).background(#603158);
	smooth();
	//frameRate(1);
	drawCircle(width/2,height/2,width, true);
}

void draw() {
	

	H.drawStage();


}

//(x,y)=position; d = diameter
void drawCircle(float x, float y, float d, boolean isBlue){
	
	HEllipse circle = new HEllipse();
	int red = #fbfbcc;
	int blue = #99cccc;
	int currentColor;

	if (isBlue){
		currentColor=blue;
	}
	else {
		currentColor=red;
	}

	
	circle
		.loc(x,y)
		.size(d)
		.anchorAt(H.CENTER)
		.strokeWeight(2)
		.stroke(#000000)
		.fill(currentColor,100)//alpha 100
	;

	



	if (d>40){

		H.add(circle);
		drawCircle(x-d/4, y-d/4, d/2, !isBlue);
		drawCircle(x-d/4, y+d/4, d/2, !isBlue);
		drawCircle(x+d/4, y-d/4, d/2, !isBlue);
		drawCircle(x+d/4, y+d/4, d/2, !isBlue);

	}

}	

		


		