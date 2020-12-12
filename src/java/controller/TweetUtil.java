/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Hashtag;
import business.Mention;
import business.Tweet;
import business.TweetHashtag;
import business.User;
import dataaccess.HashtagDB;
import dataaccess.MentionDB;
import dataaccess.TweetHashtagDB;
import dataaccess.UserDB;

/**
 *
 * @author Duy Pham
 */
public class TweetUtil {
    
    public static String highlightMention(String twit){
        String newTwit = twit;
        int startInd = 0;
        while(twit.indexOf("@", startInd) != -1){
            int indexOf = twit.indexOf("@", startInd);
            int indexOfSpace = twit.indexOf(" ", indexOf + 1);
            if(indexOfSpace == -1){
                indexOfSpace = twit.length();
            }
            String mention = twit.substring(indexOf, indexOfSpace);
            newTwit = newTwit.replace(mention, "<a class='mention'>" + mention + "</a>");
            startInd = indexOf+1;
        }
        return newTwit;
    }
    
    public static void updateMentionDB(Tweet tweet)
    {
        int startInd = 0;
        String twit = tweet.getTwit();
        while(twit.indexOf("@", startInd) != -1){
            int indexOf = twit.indexOf("@", startInd);
            int indexOfEnd = twit.indexOf("<", indexOf + 1);
            if(indexOfEnd == -1){
                indexOfEnd = twit.length();
            }
            String mentionUser = twit.substring(indexOf+1, indexOfEnd);
            //Find user that is mentioned
            User user = UserDB.searchUsername(mentionUser);
            if(user != null){
                Mention mention = new Mention();
                mention.setTweetID(tweet.getTweetID());
                mention.setUserID(user.getUserID());
                MentionDB.insert(mention);
            }
            startInd = indexOf+4;
        }
    }
    
    public static String highlightHashtag(String twit){
        String newTwit = twit;
        int startInd = 0;
        while(twit.indexOf("#", startInd) != -1){
            int indexOf = twit.indexOf("#", startInd);
            int indexOfSpace = twit.indexOf(" ", indexOf + 1);
            if(indexOfSpace == -1){
                indexOfSpace = twit.length();
            }
            String hashtag = twit.substring(indexOf, indexOfSpace);
            newTwit = newTwit.replace(hashtag, "<a href=\"hashtag\" id=\"hashtag\" class=\"hashtag\">" + hashtag + "</a>");
            startInd = indexOf+1;
        }
        return newTwit;
    }
    
    public static void updateHashtagDB(Tweet tweet)
    {
        int startInd = 0;
        String twit = tweet.getTwit();
        while(twit.indexOf("#", startInd) != -1){
            int indexOf = twit.indexOf("#", startInd);
            int indexOfEnd = twit.indexOf("<", indexOf + 1);
            if(indexOfEnd == -1){
                indexOfEnd = twit.length();
            }
            String hashtagText = twit.substring(indexOf+1, indexOfEnd);
            //Find hashtag, if not exist -> add new hashtag to DB. Finally, update hashtagCount
            Hashtag hashtag = HashtagDB.searchHashtag(hashtagText);
            if(hashtag == null){
                HashtagDB.insert(hashtagText);
                hashtag = HashtagDB.searchHashtag(hashtagText);
            }
            TweetHashtag tweetHashtag = new TweetHashtag(tweet.getTweetID(), hashtag.getHashtagID());
            TweetHashtagDB.insert(tweetHashtag);
            HashtagDB.updateHashtagCount(hashtag.getHashtagID());
            
            startInd = indexOf+4;
        }
    }
}
