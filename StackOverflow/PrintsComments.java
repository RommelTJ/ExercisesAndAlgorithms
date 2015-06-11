/* The reason this works is because Unicode decoding takes place before any 
 * other lexical translation. In Java, you can write Unicode anywhere, even 
 * in comments. 
 * The unicode character below is a CR (carriage return) so it ends the 
 * Java comment and it begins the next line of valid Java code.
 */

class PrintsComments {
  public static void main(String... args) {
    // The comment below is no typo. 
    // \u000d System.out.println("Hello World!");
  }
}

