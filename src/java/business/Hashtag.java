
package business;

public class Hashtag {
    String hashtagID;
    String hashtagText;
    String hashtagCount;
    
    public Hashtag()
    {
        hashtagID = "";
        hashtagText = "";
        hashtagCount = "";
    }
    
    public Hashtag(String hashtagID, String hashtagText, String hashtagCount)
    {
        this.hashtagID = hashtagID;
        this.hashtagText = hashtagText;
        this.hashtagCount = hashtagCount;
    }
    
    public void setHashtagID(String hashtagID)
    {
        this.hashtagID = hashtagID;
    }
    
    public String getHashtagID()
    {
        return this.hashtagID;
    }
    
    public void setHashtagText(String hashtagText)
    {
        this.hashtagText = hashtagText;
    }
    public String getHashtagText()
    {
        return this.hashtagText;
    }
    
    public void setHashtagCount(String hashtagCount)
    {
        this.hashtagCount = hashtagCount;
    }
    public String getHashtagCount()
    {
        return this.hashtagCount;
    }
}
