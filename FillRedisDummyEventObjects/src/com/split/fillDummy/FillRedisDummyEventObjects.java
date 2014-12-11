package com.split.fillDummy;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;
import com.split.event.classes.*;
import com.split.event.redisConverter.*;

public class FillRedisDummyEventObjects {

	public static Jedis jedis;
	public static long recipientsKeyCounter;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jedis = new Jedis("localhost");

		//CommentEvent
		String somePollID = "1619729e-9c9b-4046-b43f-459d5f8e8176:polls:pollID:1";
		String recipientPollAuthor = "1619729e-9c9b-4046-b43f-459d5f8e8176";
		String commentAuthor = "b6798e6f-ec95-462b-a96a-6e84936d6995";
		String commentID = "1619729e-9c9b-4046-b43f-459d5f8e8176:polls:pollID:1:comments:commentID:11";
		String myPollTimeStamp = jedis.hget(somePollID, "polltimeStamp");
		String myCommentTimeStamp = jedis.hget(commentID, "commentTimeStamp");
		CommentEvent dummyCommentEvent = new CommentEvent(new String[]{recipientPollAuthor}, new String[]{commentAuthor}, somePollID, commentID, myPollTimeStamp, myCommentTimeStamp);
		EventRedisConverter.sendEventObjectToRedis(dummyCommentEvent);
		
		//FriendPollEvent
		String someUserID = "ad69fb44-a04a-4acd-904a-b04c38a1ebd7";
		String somePollID2 = someUserID + ":polls:pollID:1";
		FriendPollEvent dummyFriendPollEvent = new FriendPollEvent(someUserID + ":firstDegreeFriends", somePollID2, someUserID, jedis.hget(somePollID2, "polltimeStamp"));
		EventRedisConverter.sendEventObjectToRedis(dummyFriendPollEvent);
		
		//LikeEvent
		String commentAuthor3 = "1619729e-9c9b-4046-b43f-459d5f8e8176";
		String commentLikerID3 = "abd1807e-1851-472c-a54d-b6e9b6b02799";
		String commentID3 = "1619729e-9c9b-4046-b43f-459d5f8e8176:polls:pollID:1:comments:commentID:1";
		String commentTimeStamp3 = jedis.hget(commentID3, "commentTimeStamp");
		String pollID3 = "1619729e-9c9b-4046-b43f-459d5f8e8176:polls:pollID:1";
		LikeEvent dummyLikeEvent = new LikeEvent(new String[] {commentAuthor3}, new String[] {commentLikerID3}, commentID3, commentTimeStamp3, pollID3, "likeTimeStampDummy");
		EventRedisConverter.sendEventObjectToRedis(dummyLikeEvent);
		
		//TagEvent
		String pollID4 = "ad69fb44-a04a-4acd-904a-b04c38a1ebd7:polls:pollID:1";
		String pollTimeStamp4 = jedis.hget(pollID4, "polltimeStamp");
		String taggerID4 = "ad69fb44-a04a-4acd-904a-b04c38a1ebd7";
		String taggedContacts4 = "ad69fb44-a04a-4acd-904a-b04c38a1ebd7:polls:pollID:1:taggedPhoneNumbers";
		TagEvent dummyTagEvent = new TagEvent(taggedContacts4, pollID4, pollTimeStamp4, taggerID4);
		EventRedisConverter.sendEventObjectToRedis(dummyTagEvent);
		
		//TagJoinEvent
		String taggedContactNumber = "555-522-8243";
		String tagger5 = "ad69fb44-a04a-4acd-904a-b04c38a1ebd7";
		TagJoinEvent dummyTagJoinEvent = new TagJoinEvent(new String[] {tagger5}, taggedContactNumber);
		EventRedisConverter.sendEventObjectToRedis(dummyTagJoinEvent);
		
		//VoteEvent
		String pollID6 = "ad69fb44-a04a-4acd-904a-b04c38a1ebd7:polls:pollID:1";
		String pollTimeStamp6 = jedis.hget(pollID6, "polltimeStamp");
		String pollAuthor6 = "ad69fb44-a04a-4acd-904a-b04c38a1ebd7";
		Set<String> voters6set = jedis.smembers("ad69fb44-a04a-4acd-904a-b04c38a1ebd7:polls:pollID:1:voters");
		String[] voters6 = voters6set.toArray(new String[voters6set.size()]);
		String voteID6 = "ad69fb44-a04a-4acd-904a-b04c38a1ebd7:polls:pollID:1:votes:voteID:1";
		String voteTimeStamp6 = jedis.hget(voteID6, "voteTimeStamp");
		VoteEvent dummyVoteEvent = new VoteEvent(new String[] {pollAuthor6}, voters6, voteID6, voteTimeStamp6, pollID6, pollTimeStamp6);
		EventRedisConverter.sendEventObjectToRedis(dummyVoteEvent);
		
		System.out.println("Done with Sending Dummy Event Objects to Redis");
		jedis.close();
	}

	

}
