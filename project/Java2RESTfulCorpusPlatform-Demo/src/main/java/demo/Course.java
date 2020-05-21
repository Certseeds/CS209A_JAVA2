package demo;

// TODO, place it as model
// Example class use by Demo Server and Demo Client
// Just a plain old Java object
class Course {
    String courseName;
    int SAcount;

    public Course() {
        this.courseName = "";
        this.SAcount = -1;
    }

    public Course(String courseName, int SAcount) {
        this.courseName = courseName;
        this.SAcount = SAcount;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getSAcount() {
        return SAcount;
    }

    public void setSAcount(int SAcount) {
        this.SAcount = SAcount;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", SAcount=" + SAcount +
                '}';
    }


}
