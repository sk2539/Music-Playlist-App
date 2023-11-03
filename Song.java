public class Song 
{
    String title;
    Song next;
    Song prev;
    Song(String t, Song n, Song p)
    {
        prev = p;
        title = t;
        next = n;
    }

    public void setTitle(String input)
    {
        title = input;
    }

    public void setNext (Song x)
    {
        next = x;
    }

    public void setPrev (Song y)
    {
        prev = y;
    }

    public String getTitle()
    {
        return title;
    }

    public Song getNext()
    {
        return next;
    }

    public Song getPrev()
    {
        return prev;
    }
}