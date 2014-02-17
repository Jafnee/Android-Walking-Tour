<?php
class route {
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
        $query = "SELECT id,title,shortDesc,longDesc,hours,distance FROM walks
                    WHERE
                    id = ".$db->real_escape_string($id)."
                    LIMIT 1";
        $result = $db->query( $query );
        $row = $result->fetch_assoc();
        $this->__populate($row);
    }
    
    public function get_title()
    {
        return $this->title;
    }
    
    public function get_short_desc()
    {
        return $this->shortDesc;
    }
    
    public function get_long_desc()
    {
        return $this->longDesc;
    }
    
    public function get_time()
    {
        return $this->hours;
    }
    
    public function get_distance()
    {
        return $this->distance;
    }
    
    public function list_all()
    {
        global $db;
        $walks=array();
        
        $query = "SELECT * FROM walks";
        $result=$db->query( $query );
   
        while($row=$result->fetch_assoc())
        {
            $hold=new route();
            $hold->__populate($row);
            $walks[]=$hold;
        }
        
        return $walks;
        
    }
    
    public function save() {
        $query = "INSERT INTO walks 
                    SET
                    title = '".addslashes($this->Title)."',
                    shortDesc = '".addslashes($this->short_desc)."',
                    longDesc = '".addslashes($this->long_desc)."',
                    hours = '".addslashes($this->hours)."',
                    distance = '".addslashes($this->distance)."'";
     
        global $db;
        $db->query($query);
        $id = $db->insert_id;
        foreach($this->waypoints as $waypoint) {
            $waypoint->save($id);
        }
    }
}
?>