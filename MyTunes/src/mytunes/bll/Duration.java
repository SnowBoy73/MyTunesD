/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

/**
 *
 * @author admin
 */
public class Duration {
  /*  private int minutes;
    private int seconds;

    
    public void setDuration(int m, int s) {
        minutes = ((m >= 0 && m <= 60) ? m :0);
        seconds = ((s >= 0 && s <= 60) ? s :0);
        }
    
    public String DurationString() {
        return String.format("%02d:%02d", minutes, seconds);
    
    }
}


public double getDuration() {
        return duration;
        // Create a non-dom allocated Audio element
    /*
        var audio = document.createElement('audio');

    // Add a change event listener to the file input
    document.getElementById("fileinput").addEventListener('change', function(event){
    var target = event.currentTarget;
    var file = target.files[0];
    var reader = new FileReader();
  
    if (target.files && file) {
        var reader = new FileReader();

        reader.onload = function (e) {
            audio.src = e.target.result;
            audio.addEventListener('loadedmetadata', function(){
                // Obtain the duration in seconds of the audio file (with milliseconds as well, a float value)
                var duration = audio.duration;
            
                // example 12.3234 seconds
                console.log("The duration of the song is of: " + duration + " seconds");
                // Alternatively, just display the integer value with
                // parseInt(duration)
                // 12 seconds
            },false);
        };

        reader.readAsDataURL(file);
    }
    }, false); 
    } */
}