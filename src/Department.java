
import java.util.ArrayList;

public class Department {
    protected ArrayList<Course> generalCourses=new ArrayList<Course>();
    protected ArrayList<Course> PE=new ArrayList<Course>();
    protected ArrayList<Course> requireds=new ArrayList<Course>();
    protected ArrayList<Course> selectives=new ArrayList<Course>();
    protected ArrayList<Course> partiallyRequireds=new ArrayList<Course>();
    protected ArrayList<Boolean>requirements=new ArrayList<Boolean>();


    double generalCredits=0;
    final double GENERALCREDITSNEEDED=28;
    final double PECREDITSNEEDED=4;
    boolean graduationRequirement=false;
    {
        for(int i=0;i<5;i++){
            requirements.add(false);
        }
    }

    public void requiredJudgement(){

    }

    public void selectiveJudgement(){

    }


    public void addCourse(Course course){
        switch(course.getCategory()){
            case "通識":
                generalCourses.add(course);
                break;
            case "體育":
                PE.add(course);
                break;
            case "必修":
                requireds.add(course);
                break;
            case "選修":
                selectives.add(course);
                break;
            case "群修":
                partiallyRequireds.add(course);
                break;
        }

    }

    public void PERequirement() {
        if (PE.size() >= 4) {
            requirements.set(1, true);
        } else {
            requirements.set(1, false);
        }
    }

    public void generalRequirement(ArrayList<Course> generalCourses){
        double humanity=0;
        double society=0;
        double nature=0;
        double chinese= 0;
        double english=0;
        double total=0;
        ArrayList<Boolean>core=new ArrayList<Boolean>();
        ArrayList<Boolean>limitMarks=new ArrayList<Boolean>();
        for(int i=0;i<5;i++){
            limitMarks.add(false);
        }
        for(int i=0;i<3;i++){
            core.add(false);
        }
        boolean coreMark=false;
        boolean limitMark=false;
        boolean passMark=false;



        for(Course course:generalCourses){
            if(course.getSubcategory().contains("中文")){
                chinese = generalLimitRequirement(course,chinese, 3, 6);
                limitMark=generalLimitRequirement(course,3,6);
                limitMarks.set(3,limitMark);
            }else if(course.getSubcategory().contains("英文")){
                english = generalLimitRequirement(course, english, 6, 6);
                limitMark=generalLimitRequirement(course,6,6);
                limitMarks.set(4,limitMark);
            }else if(course.getSubcategory().contains("人文")){
                if(course.getSubcategory().contains("核通")){
                    core.set(0, true);
                    humanity=generalLimitRequirement(course,humanity,3,9);
                    limitMark=generalLimitRequirement(course,3,9);
                    limitMarks.set(0,limitMark);
                }else{
                    humanity=generalLimitRequirement(course,humanity,3,9);
                    limitMark=generalLimitRequirement(course,3,9);
                    limitMarks.set(0, limitMark);
                }
            }else if(course.getSubcategory().contains("社會")){
                if(course.getSubcategory().contains("核通")){
                    core.set(1, true);
                    society=generalLimitRequirement(course,society,3,9);
                    limitMark=generalLimitRequirement(course,3,9);
                    limitMarks.set(1,limitMark);
                }else{
                    society=generalLimitRequirement(course,society,3,9);
                    limitMark=generalLimitRequirement(course,3,9);
                    limitMarks.set(1, limitMark);
                }

            }else if(course.getSubcategory().contains("自然")){
                if(course.getSubcategory().contains("核通")){
                    core.set(2, true);
                    nature=generalLimitRequirement(course,nature,3,9);
                    limitMark=generalLimitRequirement(course,3,9);
                    limitMarks.set(2,limitMark);
                }else{
                    nature=generalLimitRequirement(course,nature,3,9);
                    limitMark=generalLimitRequirement(course,3,9);
                    limitMarks.set(2, limitMark);
                }

            }
            generalCredits=humanity+society+nature+chinese+english;
        }


        if(generalCredits>28){
            for(boolean mark:core){
                if(!mark){
                    passMark=false;
                    requirements.set(1,passMark);
                    int coreNum=0;
                    for(boolean num:core){
                        if(!num){
                            coreNum++;
                        }
                    }
                    System.out.printf("%.2d core general course needed\n",coreNum);
                }else{
                    passMark=true;
                    requirements.set(1,passMark);
                }
            }
            passMark=false;
            requirements.set(1,passMark);
            System.out.printf("total general course credits: %.2f\n",generalCredits);

        }else{
            passMark=false;
            requirements.set(1,passMark);
            System.out.printf("total general course credits: %.2f\n",generalCredits);
        }
        if (!passMark){
            System.out.println("general course credits pass");
        }

        //System.out.printf("%.2f general course credits needed\n",GENERALCREDITSNEEDED-generalCredits);
    }



    public boolean generalLimitRequirement(Course course,double min,double max){
        if(course.getCredits()<min){
            return false;
        }else if(course.getCredits()>max){
            System.out.printf("exceed %-4s higher limit\n",course.getSubcategory());
            return true;
        }else{
            return true;
        }


    }
    public double generalLimitRequirement(Course course,double credits,double min,double max){
        if(course.getCredits()<min){
            credits+=course.getCredits();
            return credits;
        }else if(course.getCredits()>max){
            System.out.printf("exceed %-4s higher limit\n",course.getSubcategory());
            credits=max;
            return credits;
        }else{
            credits+=course.getCredits();
            return credits;
        }


    }

    public void summarize(){
        int counter=0;
        for(boolean pass:requirements){
            if(!pass){
                counter++;
                break;
            }else{
                graduationRequirement=true;
                counter++;
            }
            counter++;





        }

    }




}
