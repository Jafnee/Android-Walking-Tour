<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of image
 *
 * @author lews
 */
class image {
    //put your code here
    
    private $bytecode;
    private $filename;
    
    public function __construct($bytecode) {
        $this->bytecode=$bytecode;
    }
    
    public function get_bytecode() {
        return $this->bytecode;
    }
    
    public function get_filename() {
        return $this->filename;
    }
}

?>
