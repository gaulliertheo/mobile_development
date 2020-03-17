package com.project.musicapp;

import java.util.ArrayList;

public class ResponseDataArtistSpotify {
    Artists ArtistsObject;

    // Getter Methods
    public Artists getArtists() {
        return ArtistsObject;
    }

    class Artists {
        private String href;
        ArrayList<Object> items = new ArrayList<Object>();
        private float limit;
        private String next;
        private float offset;
        private String previous = null;
        private float total;


        // Getter Methods


        public String getHref() {
            return href;
        }


        public float getLimit() {
            return limit;
        }


        public String getNext() {
            return next;
        }


        public float getOffset() {
            return offset;
        }


        public String getPrevious() {
            return previous;
        }


        public float getTotal() {
            return total;
        }
    }

    class Object {

        Followers FollowersObject;
        ArrayList<Object> genres = new ArrayList<Object>();
        private String href;
        private String id;
        ArrayList<ImageObject> images = new ArrayList<ImageObject>();
        private String name;
        private float popularity;
        private String type;
        private String uri;

        public Followers getFollowers() {
            return FollowersObject;
        }

        public String getHref() {
            return href;
        }

        public String getId() {
            return id;
        }


        public String getName() {
            return name;
        }


        public float getPopularity() {
            return popularity;
        }


        public String getType() {
            return type;
        }


        public String getUri() {
            return uri;
        }
    }

    class Followers {

        private String total;

        public String getTotal() {
            return total;
        }
    }

    class ImageObject{
        private float height;
        private String url;
        private float width;

        public float getHeight() {
            return height;
        }

        public String getUrl() {
            return url;
        }

        public float getWidth() {
            return width;
        }
    }
}
