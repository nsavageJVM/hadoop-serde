# -*- coding: utf-8 -*-
import tweepy
import json

class StreamProcessor(tweepy.StreamListener):
    def __init__(self, api, numtweets=0):
        self.api = api
        self.count = 0
        self.limit = int(numtweets)
        super(tweepy.StreamListener, self).__init__()

    def on_data(self, tweet):
        tweet_data = json.loads(tweet)
        if ('text' in tweet_data) and ( tweet_data['user']['lang']== 'en' ): 
            #print tweet.rstrip()
	    print tweet_data['text'].encode("utf-8").rstrip()
            self.count = self.count+1
        return False if self.count == self.limit else True

auth = tweepy.OAuthHandler("xxxxxx", "xxxxxxxxxx")
auth.set_access_token("xxxxxxxxxx", "xxxxxxxxxxx")
api = tweepy.API(auth)
sapi = tweepy.streaming.Stream(auth, StreamProcessor( api=api, numtweets=15))
sapi.sample()





