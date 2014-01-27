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
        
        $query = "SELECT id,walkID,latitude,longitude,timestamp FROM locations
                    WHERE
                    walkID='".$db->real_escape_string($walkID)."'";
        
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
    
    /*Save the waypoint*/
    public function save($data) {
        
    }
    
    /*Transfer the image*/
    public function transfer_image()
    {
        
    }
}
?>