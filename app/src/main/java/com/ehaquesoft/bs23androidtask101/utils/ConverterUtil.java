package com.ehaquesoft.bs23androidtask101.utils;

public class ConverterUtil {

   public static final String JAVA = "Java";
   public static final String CPP = "C++";
   public static final String C = "C";
   public static final String JAVA_SCRIPT = "JavaScript";
   public static final String PYTHON = "Python";
   public static final String KOTLIN = "Kotlin";
   public static final String DART = "Dart";
   public static final String SWIFT = "Swift";
   public static final String FLUTTER = "Flatter";

   public static String emptyConvert(String original, String left, String right){
      if(null == original || original.isEmpty())
         return "";

      return " "+left + " "+ original + " "+ right +" ";

   }

   public static String emptyConvert(String original, String left){
      if(null == original || original.isEmpty())
         return "";

      return " "+left + " "+ original +" ";
   }

   public static String emptyConvert(String original){
      if(null == original || original.isEmpty())
         return "";

      return original;

   }

   public static String languageImageUrl(String language){
      if(null == language || language.isEmpty())
         return "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png";

      if(language.equals(JAVA)){
         return "https://raw.githubusercontent.com/github/explore/5b3600551e122a3277c2c5368af2ad5725ffa9a1/topics/java/java.png";
      }else if(language.equals(CPP)){
         return "https://raw.githubusercontent.com/github/explore/180320cffc25f4ed1bbdfd33d4db3a66eeeeb358/topics/cpp/cpp.png";
      }else if(language.equals(JAVA_SCRIPT)){
         return "https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/javascript/javascript.png";
      }else if(language.equals(C)){
         return "https://raw.githubusercontent.com/github/explore/f3e22f0dca2be955676bc70d6214b95b13354ee8/topics/c/c.png";
      }else if(language.equals(PYTHON)){
         return "https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/python/python.png";
      }else if(language.equals(SWIFT)){
         return "https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/swift/swift.png";
      }else if(language.equals(DART)){
         return "https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/dart/dart.png";
      }else if(language.equals(FLUTTER)){
         return "https://raw.githubusercontent.com/github/explore/cebd63002168a05a6a642f309227eefeccd92950/topics/flutter/flutter.png";
      }else if(language.equals(KOTLIN)){
         return "https://raw.githubusercontent.com/github/explore/4479d2a2c854198cb00160f8593519c14dc3b905/topics/kotlin/kotlin.png";
      }else{
         return "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png";
      }





   }
}
