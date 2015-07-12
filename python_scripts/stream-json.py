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
        with open('raw_twitter_json', 'w') as f:
            f.write(tweet.rstrip())
        print tweet.rstrip()
        self.count = self.count+1
        return False if self.count == self.limit else True

auth = tweepy.OAuthHandler("LigCUWP7pN1lAWFiCpvIUeHRq", "ypFPAqO93ndoG4kUGfXJx2MhioJuPN28XTNSMobftlMmZfTtcL")
auth.set_access_token("3347110648-cqofWRFUTTawCJ7mSBOUL8qXwKQqZ3qoRYX60PK", "lC7Z3PSpQWVhFCjTPvczrG5NVcnpgld7fkj3rnWNHEnut")
api = tweepy.API(auth)
sapi = tweepy.streaming.Stream(auth, StreamProcessor( api=api, numtweets=15))
sapi.sample()






