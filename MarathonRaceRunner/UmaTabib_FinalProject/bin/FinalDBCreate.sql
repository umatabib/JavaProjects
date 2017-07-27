CONNECT 'jdbc:derby:FinalDB;create=true';

-- drop tables (ignore errors if they don't exist)
--DROP TABLE RunnerInfo;


-- create RunnerInfo table
CREATE TABLE RunnerInfo
(
    Name VARCHAR(20), 
	RunnersSpeed DOUBLE, 
	RestPercentage DOUBLE
);


-- insert data into RunnerInfo table
INSERT INTO RunnerInfo VALUES
('Uma', 50 , 10);   

INSERT INTO RunnerInfo VALUES
('Ketan', 70 , 30);

INSERT INTO RunnerInfo VALUES
('Rushi', 100 , 80);	


-- view data in RunnerInfo table
SELECT * FROM RunnerInfo;


DISCONNECT;