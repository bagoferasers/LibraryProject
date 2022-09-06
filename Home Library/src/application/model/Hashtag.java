package application.model;

public class Hashtag {
	 String[] hashtagNames;
	 
	 public Hashtag(String[] n) {
		 this.hashtagNames = n;
	 }
	 
	 @Override
	 public String toString() {
		 String s = "";
		 for(int i = 0; i < this.hashtagNames.length; i++) {
			 s += "#" + this.hashtagNames[i] + " ";
		 }
		 return s.toString();
	 }
}
