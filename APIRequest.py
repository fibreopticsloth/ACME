
import csv
import json
import requests
import time

#
# Send request to OMDb
#
def send_request(userSearch):
    apiKey = "9d6240b0"
    api_url = "http://www.omdbapi.com/?apikey=%s&%s" % (apiKey, userSearch)

    params = {
        't':    '%s' %userSearch
    }
    #send request to API
    response = requests.get(api_url, params=params)

    if response.status_code == 200:
        return json.loads(response.content)
    
    return None


def movie_search(userSearch):
    full_movie_list = []
    
    movie_list = send_request(userSearch)
            
    if movie_list is None or len(movie_list) == 0:
        print "No movie with that name"
            
    
    full_movie_list.extend(movie_list)

    print '[*] Retrieved Movies' 

    #return movie
    return movie_list

userSearch = raw_input("Please enter the movie you would like to search: \n")
movie_list = movie_search(userSearch)


print 'Name: %s\t Release Date: %s\t Plot: %s' %(movie_list['Title'], movie_list['Year'], movie_list['Plot'])
    
close = raw_input("Please hit enter to close this program")
