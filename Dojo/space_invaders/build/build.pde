int sz = 15;
int step = sz/5; // = 4
int padding = int(sz/2);
int mx,my;
ArrayList invaders;

void setup() {
  mx = int(900/(sz+padding));
  my = int(600/(sz+padding));
  size(887,601);
  background(255);
  stroke(0);

  invaders = new ArrayList();

  for (float x=padding; x<width-2*padding; x += (sz+padding)) {
    for (float y=padding; y<height-2*padding; y += (sz+padding)) {
      Invader invader = new Invader(x, y);
      invaders.add(invader);
    }
  }
  for (int i=0; i<invaders.size();i++) {
    Invader inv = (Invader) invaders.get(i);
    inv.display();
  }
}

void draw() {
}

void keyPressed() {
  save(random(1234)+".gif");
}
void mouseClicked() {
  setup();
}

class Invader {
  float x, y;
  int c, m ;
  int[][] col = new int[sz][sz];

  Invader(float _x, float _y) {
    x = _x;
    y = _y;
    //c = 155;

    for (int j=0;j<sz;j+=step) {
      m = 1;
      for (int i=0;i<sz/2;i+=step) {
        c = (random(1) > .5)? 255:0; //black or white?
        col[j][i]= c;
        col[j][i+(sz-step)/m] = c;
        m++;
      }
    }
  }

  void display() {
    fill(c);
    stroke(c);
    for (int j=0;j<sz; j+=step) {
      for (int i=0;i<sz; i+= step) {
        fill(col[j][i]);
        noStroke();
        rect(x+i, y+j, step, step);
      }
    }
  }
}
