package springframework.guru.movieeventservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MovieEvent {
        private String eventMessage;
        private Date date;
    }

