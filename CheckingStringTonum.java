package Checking;

public class CheckingStringTonum {
	public static boolean isConvPosible(String s) {
		byte dot=1;
		for(int i = 0;i<s.length();i++) {
			if(s.charAt(i)=='+'||s.charAt(i)=='-'){
			    if(i==0)continue;
			    else return false;
			}
			else if(s.charAt(i)=='.') {
				if(dot==0) {
					return false;
				}
				dot=0;
			}
			else if(s.charAt(i)>=48&&s.charAt(i)<=57) {
				continue;
			}
			else {
				return false;
			}
		}
		return true;
	}
}
