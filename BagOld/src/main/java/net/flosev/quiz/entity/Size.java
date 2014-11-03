package net.flosev.quiz.entity;

/**
 * Created with IntelliJ IDEA.
 * User: виталик
 * Date: 21.06.14
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
public class Size {

    private String article;
    private int length;
    private int width;
    private int heigth;

        public Size(String article, int length, int width, int heigth ) {
            this.article=article;
            this.length = length;
            this.width = width;
            this.heigth = heigth;

        }

    public String getArticle () {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getLength () {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth () {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth () {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }



       /* @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Theme that = (Theme) o;
            // todo: only id or all fields?
            return !(id != null ? !id.equals(that.id) : that.id != null);
        }

        @Override
        public int hashCode() {
            // todo: only id or all fields?
            return id != null ? id.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "Theme{" +
                    "id='" + id + '\'' +
                    ", caption='" + caption + '\'' +
                    '}';
        }
    }*/

}
