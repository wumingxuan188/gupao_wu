public class Test {

    public static void main(String[] args) {
        String pointCut ="public .* com.gupaoedu.vip.demo.service..*Service..*(.*)"
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");
      //  System.err.println("public .* com.gupaoedu.vip.demo.service..*Service..*(.*)");
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        System.err.println(pointCutForClassRegex);
        String s = "class " + pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") + 1);

        System.err.println(s);
    }
}
