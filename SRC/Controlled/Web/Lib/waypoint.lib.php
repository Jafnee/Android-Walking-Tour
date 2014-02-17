<?php
class waypoint {
    public function __construct() {}
    
    /*Populates the object from an array*/
    public function __populate($data) 
    {
        if(!is_array($data)) return NULL;
        foreach($data as $key => $val)
        {
            $this->$key = $val;
        }
    }
    
    public function load($id)
    {
        if(!$id) return NULL;
        
        global $db;
        $query = "SELECT id,walkID,latitude,longitude,timestamp FROM locations
                    WHERE
                    id = '".$db->real_escape_string($id)."'
                    LIMIT 1";
        $result = $db->query( $query );
        $row = $result->fetch_assoc();
        $this->__populate($row);
    }
    
    
    public function load_walk($walkID)
    {
        if(!$walkID) return NULL;
        global $db;
        $walks=array();
        
        $query = "SELECT * FROM location
                    WHERE
                    walkID='".$db->real_escape_string($walkID)."' ORDER BY timestamp DESC";
     
        $result=$db->query( $query );
        while($row=$result->fetch_assoc())
        {
            $hold=new waypoint();
            $hold->__populate($row);
            $walks[]=$hold;
        }
        
        return $walks;
    }
    
    public function get_latitude()
    {
        return $this->latitude;
    }
    
    public function get_longitude()
    {
        return $this->longitude;
    }
    
    /*Return associated waypoint description from the database*/
    public function get_description()
    {
        if(!$this->id) return NULL;
        global $db;
        
        $query = "SELECT description FROM description
                    WHERE
                    locationID='".$db->real_escape_string( $this->id )."'";
        
        $result = $db->query( $query );
        $row = $result->fetch_assoc();
        return $row['description'];
        
    }
    
    /*Return associated images from the database*/
    public function get_images()
    {
        if(!$this->id) return NULL;
        
        global $db;
        $query = "SELECT photoName FROM photo
                    WHERE
                    locationID='".$db->real_escape_string( $this->id )."'";
        $result = $db->query( $query );
        $images = array();
        if($result)
        {
            while($row=$result->fetch_assoc()) {
                $images[] = $row['photoName'];
            }
        }
        
        return $images;
    }
    /*Save the waypoint*/
    public function save($walkID) {
        $query = "INSERT INTO location SET walkID = '".addslashes($walkID)."', latitude = '".addslashes($this->latitude)."', 
            longitude = '".addslashes($this->longitude)."', timestamp = '".addslashes($this->timestamp)."'";
        
        global $db;
        $db->query($query);
        
        $id = $db->insert_id;
        
        $query = "INSERT INTO description SET locationID ='".addslashes($id)."', description = '".addslashes($this->desc)."'";
        
        $db->query($query);
    }
    
    /*Transfer the image*/
    public function transfer_image()
    {
        
    }
}
?>