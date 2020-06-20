package com.example.timemanager;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "event table")
//    public class Event {
//        @PrimaryKey(autoGenerate = true)
//        private int id;
//        @Ignore private String title;
//        @Ignore private String description;
//        @Ignore private String dueDate;
//        @Ignore private String MinOrHours;
//        @Ignore private String amountOfTime;
//        @Ignore private int priority;
//
//        public Event(String title, String description, String dueDate, String minOrHours, String amountOfTime, int priority) {
//            this.title = title;
//            this.description = description;
//            this.dueDate = dueDate;
//            this.MinOrHours = minOrHours;
//            this.amountOfTime = amountOfTime;
//            this.priority = priority;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public void setDueDate(String dueDate) {
//            this.dueDate = dueDate;
//        }
//
//        public void setMinOrHours(String minOrHours) {
//            MinOrHours = minOrHours;
//        }
//
//        public void setAmountOfTime(String amountOfTime) {
//            this.amountOfTime = amountOfTime;
//        }
//
//        public void setPriority(int priority) {
//            this.priority = priority;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public String getDueDate() {
//            return dueDate;
//        }
//
//        public String getMinOrHours() {
//            return MinOrHours;
//        }
//
//        public String getAmountOfTime() {
//            return amountOfTime;
//        }
//
//        public int getPriority() {
//            return priority;
//        }
//    }



public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
     private String dueDate;
     private String MinOrHours;
   private String amountOfTime;
    private int priority;


    public Event(String title, String description, String dueDate, String MinOrHours, String amountOfTime, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.MinOrHours = MinOrHours;
        this.amountOfTime = amountOfTime;
    }


    public String getDueDate() {
        return dueDate;

    }

    public String getMinOrHours() {
        return MinOrHours;
    }

    public String getAmountOfTime() {
        return amountOfTime;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getPriority() {
        return priority;

    }
}
