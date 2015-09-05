
HColorPool colors;

void setup(){
	size(600,600);
	H.init(this).background(#603158);
	colors=new HColorPool(#fbfbcc, #99cccc);
	smooth();
	//frameRate(1);
	drawCircle(width/2,height/2,width);
}

void draw() {
	

	H.drawStage();


}

//(x,y)=position; d = diameter
void drawCircle(float x, float y, float d){
	
	HEllipse circle = new HEllipse();
	int currentColor=colors.getColor();

	
	circle
		.loc(x,y)
		.size(d)
		.anchorAt(H.CENTER)
		.strokeWeight(1+(int) random (2))
		.stroke(#000000)
		.fill(currentColor,75)//alpha 100
	;

	



	if (d>40){

		H.add(circle);
		drawCircle(x-d/4, y-d/4, d/2);
		drawCircle(x-d/4, y+d/4, d/2);
		drawCircle(x+d/4, y-d/4, d/2);
		drawCircle(x+d/4, y+d/4, d/2);

	}

}	

		


		