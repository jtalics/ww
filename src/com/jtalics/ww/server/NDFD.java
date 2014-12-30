package com.jtalics.ww.server;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
public class NDFD {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	/*
	 * http://www.nws.noaa.gov/ndfd/technical.htm /*
	 * http://www.nws.noaa.gov/ndfd/definitions.htm
	 */
	// var.) Element name, No. of Grids, Projections
	// * 13 issuances for months 1 - 3, months 2 - 4, months 3 - 5, ..., up to
	// months 13 -15

	public static final String a1Desc = " A1.) 12-hour Probability of Precipitation (PoP12), 13-15, Every 12 hours, out to 168 hours|12-HOUR PROBABILITY OF PRECIPITATION PoP12 is the likelihood, expressed as a percent, of a measurable precipitation event (1/100th of an inch or more) at a grid point during the 12-hour valid period. The 12-hour valid periods begin and end at 0000 and 1200 Coordinated Universal Time (UTC).";
	@Basic
	Float a1;

	public static final String a2Desc = " A2.) Apparent Temperature, 33-41, Every 3 hours out to 72 hours; every 6 hours out to 168 hours|APPARENT TEMPERATURE is the perceived temperature in degrees Fahrenheit derived from either a combination of temperature and wind (Wind Chill) or temperature and humidity (Heat Index) for the indicated hour. When the temperature at a particular grid point falls to 50°F or less, wind chill will be used for that point for the Apparent Temperature. When the temperature at a grid point rises above 80°F, the heat index will be used for Apparent Temperature. Between 51 and 80°F, the Apparent Temperature will be the ambient air temperature.";
	Float a2;

	public static final String a3Desc = " A3.) Dew Point, 33-41, Every 3 hours out to 72 hours; every 6 hours out to 168 hours|DEW POINT is the expected dew point temperature in degrees Fahrenheit for the indicated hour. Dew point temperature is a measure of atmospheric moisture. It is the temperature to which air must be cooled to reach saturation (assuming air pressure and moisture content are constant).";
	@Basic
	Float a3;

	public static final String a4Desc = " A4.) Hazards 59-82; Every hour, out to 72 hours; every 6 hours out to 120 hours|HAZARDS are weather or hydrologic hazardous events issued for the protection of life and property and the enhancement of the national economy. For a specific list of hazards available through NDFD, see the Product Description Document entitled “Experimental Hazard Grids in the National Digital Forecast Database http://products.weather.gov/PDD/HazardGrid0608.pdf”, Appendix A.";
	@Basic
	String a4;

	public static final String a5Desc = "A5.) Maximum Temperature, 7-8, Every 24 hours, out to 168 hours|MAXIMUM/MINIMUM TEMPERATURE is the daytime maximum or the overnight minimum temperature in degrees Fahrenheit.";
	@Basic
	Float a5;

	public static final String a6Desc = "A6.) Minimum Temperature, 6-7, Every 24 hours, out to 168 hours|MAXIMUM/MINIMUM TEMPERATURE is the daytime maximum or the overnight minimum temperature in degrees Fahrenheit.";
	@Basic
	Float a6;

	public static final String a7Desc = "A7.) Quantitative Precipitation Amount, 9-13, Every 6 hours out to 72 hours|QUANTITATIVE PRECIPITATION FORECAST (QPF) is the expected amount of liquid precipitation (in hundredths of inches) accumulated over a six hourly period. A QPF will be specified when a measurable ( 1/100th of an inch or more) precipitation type is forecast for any hour during a QPF valid period. NDFD valid periods for QPF are 6 hours long beginning and ending at 0000, 0600, 1200 and 1800 UTC.";
	@Basic
	Float a7;

	public static final String aDesc = " A8.) Relative Humidity, 33-41, Every 3 hours out to 72 hours; every 6 hours out to 168 hours|RELATIVE HUMIDITY is a ratio, expressed as a percent, of the amount of atmospheric moisture present relative to the amount that would be present if the air were saturated.  Since the latter amount is dependent on temperature, relative humidity is a function of both moisture content and temperature.";
	@Basic
	Float a8;

	public static final String a9Desc = "A9.) Significant Wave Height, 17-21, Every 6 hours out to 120 hours|SIGNIFICANT WAVE HEIGHT is the average height in feet (from trough to crest) of the one-third highest waves for the indicated 12-hour period. The 12-hour periods begin and end at 0000 and 1200 UTC.";
	@Basic
	Float a9;

	public static final String a10Desc = "A10.) Sky Cover, 33-41, Every 3 hours out to 72 hours;every 6 hours out to 168 hours|SKY COVER is the expected amount of opaque clouds (in percent) covering the sky valid for the indicated hour.";
	@Basic
	Float a10;
	
	public static final String a11Desc = "A11.) Snow Amount, 5-9, Every 6 hours out to 48 hours|SNOW AMOUNT is the expected total accumulation of new snow (in inches) during a 6 hour period. A snow accumulation grid will be specified whenever a measurable snowfall is forecast for any hour during a valid period. Valid periods for the NDFD begin and end at 0600, 1200, 1800, and 0000 UTC.";
	@Basic
	Float a11;
	
	public static final String a12Desc = "A12.) Temperature, 33-41, Every 3 hours out to 72 hours; every 6 hours out to 168 hours|TEMPERATURE is the expected temperature in degrees Fahrenheit valid for the indicated hour.";
	@Basic
	Float a12;
	
	public static final String a13Desc = "A13.) Weather, 33-41, Every 3 hours out to 72 hours; every 6 hours out to 168 hours|WEATHER is the expected weather (precipitating or non-precipitating) valid at the indicated hour. Precipitating weather includes type, probability, and intensity information. In cases of convective weather, coverage may be substituted for probability.";
	@Basic
	String a13;
	
	public static final String a14Desc = "A14.) Wind Direction, 33-41, Every 3 hours out to 72 hours; every 6 hours out to 168 hours|WIND DIRECTION is the expected sustained 10-meter wind direction for the indicated hour, using 36 points of a compass. Click here for information on interpreting wind barbs.";
	@Basic
	Float a14;
	
	public static final String a15Desc = "A15.) Wind Gust, 17-25, Every 3 hours out to 72 hours|WIND GUST is the maximum 3-second wind speed (in knots) forecast to occur within a 2-minute interval at a height of 10 meters. Wind gust forecasts are valid at the top of the indicated hour.";
	@Basic
	Float a15;
	
	public static final String a16Desc = "A16.) Wind Speed, 33-41, Every 3 hours out to 72 hours; every 6 hours out to 168 hours|WIND SPEED is the expected sustained 10-meter sustained wind speed (in knots) for the indicated hour.";
	@Basic
	Float a16;
	
	public static final String b1Desc = "B1.) 8- to 14-day Avg Temperature Above Normal, 1, 14 days (issued once daily)|8- TO 14-DAY AVERAGE TEMPERATURE ABOVE NORMAL is the probability, expressed as a percent, of above normal (median) categories of 7-day mean temperature at a lead-time of 1 week.";
	@Basic
	Float b1;
	
	public static final String b2Desc = "B2.) 8- to 14-day Avg Temperature Below Normal, 1, 14 days (issued once daily)|8- TO 14-DAY AVERAGE TEMPERATURE BELOW NORMAL is the probability, expressed as a percent, of below normal (median) categories of 7-day mean temperature at a lead-time of 1 week.";
	@Basic
	Float b2;
	
	public static final String b3Desc = "B3.) 8- to 14-day Total Precipitation Above Median, 1, 14 days (issued once daily)|8- TO 14-DAY TOTAL PRECIPITATION ABOVE NORMAL is the probability, expressed as a percent, of above normal (median) categories of 7-day total precipitation at a lead-time of 1 week.";
	@Basic
	Float b3;
	
	public static final String b4Desc = "B4.) 8- to 14-day Total Precipitation Below Median, 1, 14 days (issued once daily)|8- TO 14-DAY TOTAL PRECIPITATION BELOW NORMAL is the probability, expressed as a percent, of below normal (median) categories of 7-day total precipitation at a lead-time of 1 week.";
	@Basic
	Float b4;
	
	public static final String b5Desc = "B5.) One-month Avg Temperature Above Normal, 1, 45 days (issued twice on 3rd Thurs of month)|ONE-MONTH AVERAGE TEMPERATURE ABOVE NORMAL is the probability, expressed as a percent, of above normal (median) categories of one-month mean temperature at a lead-time of ½-month.";
	@Basic
	Float b5;
	
	public static final String b6Desc = "B6.) One-month Avg Temperature Below Normal, 1, 45 days (issued twice on 3rd Thurs of month)|ONE-MONTH AVERAGE TEMPERATURE BELOW NORMAL is the probability, expressed as a percent, of below normal (median) categories of one-month mean temperature at a lead-time of ½-month.";
	@Basic
	Float b6;
	
	public static final String b7Desc = "B7.) One-month Total Precipitation Above Median, 1, 45 days (issued twice 3rd Thurs each month)|ONE-MONTH TOTAL PRECIPITATION ABOVE NORMAL is the probability, expressed as a percent, of above normal (median) categories of one-month total precipitation at a lead-time of ½-month.";
	@Basic
	Float b7;
	
	public static final String b8Desc = "B8.) One-month Total Precipitation Below Median, 1, 45 days (issued twice 3rd Thurs each month)|ONE-MONTH TOTAL PRECIPITATION BELOW NORMAL is the probability, expressed as a percent, of below normal (median) categories of one-month total precipitation at a lead-time of ½-month.";
	@Basic
	Float b8;
	
	public static final String b9Desc = "B9.) Three-month Avg Temperature Above Normal, 13*,  375 days (issued twice 3rd Thurs each month)|THREE-MONTH AVERAGE TEMPERATURE ABOVE NORMAL is the probability, expressed as a percent, of above normal categories of 3-month mean temperature at lead-times ranging from ½-month to 12-1/2 months.";
	@Basic
	Float b9;
	
	public static final String b10Desc = "B10.) Three-month Avg Temperature Below Normal, 13*, 375 days (issued twice 3rd Thurs each month)|THREE-MONTH AVERAGE TEMPERATURE BELOW NORMAL is the probability, expressed as a percent, of below normal categories of 3-month mean temperature at lead-times ranging from ½-month to 12-1/2 months).";
	@Basic
	Float b10;
	
	public static final String b11Desc = "B11.) Three-month Total Precipitation Above Median, 13*, 375 days (issued twice 3rd Thurs each month)|THREE-MONTH TOTAL PRECIPITATION ABOVE NORMAL is the probability, expressed as a percent, of above normal categories of 3-month total precipitation at lead-times ranging from ½-month to 12-1/2 months.";
	@Basic
	Float b11;
	
	public static final String b12Desc = "B12.) Three-month Total Precipitation Below Median, 13*, 375 days (issued twice 3rd Thurs each month)|THREE-MONTH TOTAL PRECIPITATION BELOW NORMAL is the probability, expressed as a percent, of below normal categories of 3-month total precipitation at lead-times ranging from ½-month to 12-1/2 months.";
	@Basic
	Float b12;
	
	public static final String c1Desc = "C1.) Convective Hazard Outlook, 3, Days 1, 2, and 3|CATEGORICAL CONVECTIVE HAZARD OUTLOOK is a categorical forecast (slight, moderate, or high risk) that specifies the perceived level of threat of thunderstorms, severe thunderstorms, hail, damaging winds, and tornadoes.";
	@Basic
	Float c1;
	
	public static final String c2Desc = "C2.) Probability of Tornadoes, 1, Day 1|PROBABILITY OF TORNADOES is the probability (in percent) of a tornado occurring within 25 miles of any point during the outlook period. The higher the probability, the higher the threat of tornadoes occurring.";
	@Basic
	Float c2;
	
	public static final String c3Desc = "C3.) Probability of Hail, 1, Day 1|PROBABILITY OF HAIL is the probability (in percent) of hail greater than three-quarters of an inch in diameter (size of a penny) occurring within 25 miles of any point during the outlook period. The higher the probability, the higher the threat of severe hail.";
	@Basic
	Float c3;
	
	public static final String c4Desc = "C4.) Probability of Damaging Thunderstorm Winds, 1, Day 1|PROBABILITY OF DAMAGING THUNDERSTORM WINDS is the probability (in percent) of winds greater than 58 miles per hour occurring within 25 miles of any point during the outlook period. The higher the probability, the higher the threat of severe thunderstorm winds occurring.";
	@Basic
	Float c4;
	
	public static final String c5Desc = "C5.) Probability of Extreme Tornadoes, 1, Day 1|PROBABILITY OF EXTREME TORNADOES is the probability (in percent) of Enhanced Fujita scale 2 (EF2) tornadoes occurring within 25 miles of any point during the outlook period. The higher the probability, the higher the threat of extreme tornadoes.";
	@Basic
	Float c5;
	
	public static final String c6Desc = "C6.) Probability of Extreme Hail, 1, Day 1|PROBABILITY OF EXTREME HAIL is the probability (in percent) of hail greater than 2 inches in diameter within 25 miles of any point during the outlook period. The higher the probability, the higher the threat of extreme hail occurring.";
	@Basic
	Float c6;
	
	public static final String c7Desc = "C7.) Probability of Extreme Thunderstorm Winds, 1, Day 1|PROBABILITY OF EXTREME THUNDERSTORM WINDS is the probability (in percent) of winds greater than 75 miles per hour occurring within 25 miles of any point during the outlook period. The higher the probability, the higher the threat of extreme thunderstorm winds occurring.";
	@Basic
	Float c7;
	
	public static final String c8Desc = "C8.) Total Probability of Severe Thunderstorms, 2, Days 2 and 3|TOTAL PROBABILITY OF SEVERE THUNDERSTORMS is the probability in percent of tornadoes, damaging winds with speeds greater than 58 miles per hour, or large hail three quarters of an inch in diameter (penny-size) occurring within 25 miles of any point during the outlook period.";
	@Basic
	Float c8;
	
	public static final String c9Desc = "C9.) Total Probability of Extreme Severe Thunderstorms|TOTAL PROBABILITY OF EXTREME SEVERE THUNDERSTORMS is the probability in percent of EF2 (Enhanced Fujita scale 2) tornadoes, damaging winds with speeds greater than 75 miles per hour, or large hail two inches or greater in diameter occurring within 25 miles of any point during the outlook period.";
	@Basic
	Float c9;
	
	public static final String d1Desc = "D1.) >34kts (incremental), 17-21, Every 6 hours out to 120 hours|PROBABILISTIC TROPICAL CYCLONE SURFACE WIND SPEED (INCREMENTAL) is the probability (in percent) of sustained surface wind speed greater than 34-, 50-, and 64-knots (3 separate elements) sometime during the specified forecast period (0 - 6 hours, 6 -12, 12 -18, etc.) at each specific point. These values are incremental since they can increase in value by accounting for the possibility the event might start in an earlier period and still be occurring in the specified period. NOTE: This element is provided for coastal and inland points as well as offshore locations (e.g., buoys).";
	@Basic
	Float d1;
	
	public static final String d2Desc = "D2.) >34kts (cumulative), 17-21, Every 6 hours out to 120 hours|PROBABILISTIC TROPICAL CYCLONE SURFACE WIND SPEED (CUMULATIVE) is the probability (in percent) of sustained surface wind speed greater than 34-, 50- and 64-knots (3 separate elements) sometime during the specified cumulative forecast period (0 – 6 hours, 0-12, 0-18, etc.) at each specific point. NOTE: This element is provided for coastal and inland points as well as offshore locations (e.g., buoys).";
	@Basic
	Float d2;
	
	public static final String d3Desc = "D3.) >50kts (incremental), 17-21, Every 6 hours out to 120 hours|PROBABILISTIC TROPICAL CYCLONE SURFACE WIND SPEED (INCREMENTAL) is the probability (in percent) of sustained surface wind speed greater than 34-, 50-, and 64-knots (3 separate elements) sometime during the specified forecast period (0 - 6 hours, 6 -12, 12 -18, etc.) at each specific point. These values are incremental since they can increase in value by accounting for the possibility the event might start in an earlier period and still be occurring in the specified period. NOTE: This element is provided for coastal and inland points as well as offshore locations (e.g., buoys).";
	@Basic
	Float d3;
	
	public static final String d4Desc = "D4.) >50kts (cumulative), 17-21, Every 6 hours out to 120 hours|PROBABILISTIC TROPICAL CYCLONE SURFACE WIND SPEED (CUMULATIVE) is the probability (in percent) of sustained surface wind speed greater than 34-, 50- and 64-knots (3 separate elements) sometime during the specified cumulative forecast period (0 – 6 hours, 0-12, 0-18, etc.) at each specific point. NOTE: This element is provided for coastal and inland points as well as offshore locations (e.g., buoys).";
	@Basic
	Float d4;
	
	public static final String d5Desc = "D5.) >64kts (incremental), 17-21, Every 6 hours out to 120 hours|PROBABILISTIC TROPICAL CYCLONE SURFACE WIND SPEED (INCREMENTAL) is the probability (in percent) of sustained surface wind speed greater than 34-, 50-, and 64-knots (3 separate elements) sometime during the specified forecast period (0 - 6 hours, 6 -12, 12 -18, etc.) at each specific point. These values are incremental since they can increase in value by accounting for the possibility the event might start in an earlier period and still be occurring in the specified period. NOTE: This element is provided for coastal and inland points as well as offshore locations (e.g., buoys).";
	@Basic
	Float d5;
	
	public static final String d6Desc = "D6.) >64kts (cumulative), 17-21, Every 6 hours out to 120 hours|PROBABILISTIC TROPICAL CYCLONE SURFACE WIND SPEED (CUMULATIVE) is the probability (in percent) of sustained surface wind speed greater than 34-, 50- and 64-knots (3 separate elements) sometime during the specified cumulative forecast period (0 – 6 hours, 0-12, 0-18, etc.) at each specific point. NOTE: This element is provided for coastal and inland points as well as offshore locations (e.g., buoys).";
	@Basic
	Float d6;

	class Hazards {
		/*
		 * Watch Name VTEC pp s Watch Definition Areal Flood Watch FA A
		 * Conditions are favorable for meteorological, soil, and/or hydrologic
		 * conditions to lead to flooding within 48 hours. Blizzard Watch BZ A
		 * Conditions are favorable for a blizzard event to meet or exceed
		 * Blizzard Warning criteria in the next 12 to 48 hours. Coastal Flood
		 * Watch CF A Conditions are favorable for a coastal flood event to meet
		 * Coastal Flood Warning criteria in the next 12 to 48 hours. Excessive
		 * Heat Watch EH A Conditions are favorable for an excessive heat event
		 * to meet or exceed local Excessive Heat Warning criteria in the next
		 * 12 to 48 hours. Extreme Cold Watch EC A Conditions are favorable for
		 * an extreme cold event to meet or exceed local Extreme Cold Warning
		 * criteria in the next 12 to 48 hours. Air temperatures exceeding
		 * locally defined criteria for a prolonged period of time. Typical
		 * criteria: shelter temperatures -50°F or colder and air temperatures
		 * remaining below -40°F up to the 700-mb level for at least three days.
		 * Fire Weather Watch FW A Conditions are favorable for a red flag event
		 * to meet or exceed Red Flag Warning criteria in the next 12 to 72
		 * hours. Flash Flood Watch FF A Conditions are favorable for
		 * meteorological, soil, and/or hydrologic conditions to lead to
		 * flooding within 12 hours or, a dam or levee may fail and threaten
		 * lives or property, but the threat is not deemed to be imminent. A-2
		 * Freeze Watch FZ A Conditions are favorable for a freeze event to meet
		 * or exceed Freeze Warning criteria in the next 12 to 48 hours during
		 * the locally defined growing season. Gale Watch GL A Conditions are
		 * favorable for a gale force wind event to meet the Gale Warning
		 * criteria of 34 knots (39 mph) to 47 knots (54 mph), not directly
		 * associated with a tropical cyclone, in the next 12 to 48 hours. Hard
		 * Freeze Watch HZ A Conditions are favorable for a hard freeze event to
		 * meet or exceed Hard Freeze Warning criteria in the next 12 to 48
		 * hours during the locally defined growing season. Hazardous Seas Watch
		 * SE A Conditions are favorable for a hazardous seas event to meet or
		 * exceed Hazardous Seas Warning criteria in the next 12 to 48 hours.
		 * Heavy Freezing Spray Watch UP A Conditions are favorable for a heavy
		 * freezing spray event to meet local Heavy Freezing Spray Warning
		 * criteria in the next 12 to 48 hours. High Wind Watch HW A Conditions
		 * are favorable for a high wind event to meet or exceed High Wind
		 * Warning criteria in the next 12 to 48 hours. Hurricane Force Wind
		 * Watch HF A Conditions are favorable for a hurricane force wind event
		 * to meet or exceed Hurricane Force Wind Warning criteria of 64 knots
		 * (74 mph) or greater, not associated with a tropical cyclone, in the
		 * next 12 to 48 hours. Hurricane Watch HU A Conditions are favorable
		 * for a tropical cyclone to produce hurricane force winds of 64 knots
		 * (74 mph) or greater in a specified coastal area within 36 hours
		 * except 48 hours for the western north Pacific. Hurricane Wind Watch
		 * HI A Conditions are favorable for a tropical cyclone to spread
		 * hurricane force winds of 64 knots (74 mph) or greater inland within
		 * 36 hours Lake Effect Snow Watch LE A Conditions are favorable for a
		 * lake effect snow event to meet or exceed local Lake Effect Snow
		 * Warning criteria in the next 12 to 48 hours. Lakeshore Flood Watch LS
		 * A Conditions are favorable for a lakeshore flood event to meet
		 * Lakeshore Flood Warning criteria in the next 12 to 48 hours. Severe
		 * Thunderstorm Watch SV A Conditions are favorable for severe
		 * thunderstorms to develop. Storm Watch SR A Conditions are favorable
		 * for a storm force wind event to meet Storm Warning criteria of 48
		 * knots (55 mph) to 63 knots (73 mph), not directly associated with a
		 * tropical cyclone, in the next 12 to 48 hours. Tornado Watch TO A
		 * Conditions are favorable for tornadoes to develop. Tropical Storm
		 * Watch TR A Conditions are favorable for a tropical cyclone to produce
		 * tropical storm force winds of 34 to 63 knots (39 to 73 mph) in a
		 * specified coastal area within 36 hours except 48 hours for the
		 * western north Pacific.. A-3 Tropical Storm Wind Watch TI A Conditions
		 * are favorable for a tropical cyclone to spread tropical storm force
		 * winds of 34 to 63 knots (39 to 73 mph) inland within the next 36
		 * hours. Tsunami Watch1 TS A Conditions are favorable for coastal areas
		 * to be impacted by destructive tsunami waves. Watches are issued based
		 * on seismic information without confirmation that a destructive
		 * tsunami is underway. The second highest level of tsunami alert.
		 * Typhoon Watch TY A Conditions are favorable for a tropical cyclone to
		 * produce typhoon force winds of 64 knots (74 mph) or greater in
		 * specified coastal areas in the western Pacific ocean in the next 24
		 * to 36 hours. Wind Chill Watch WC A Conditions are favorable for wind
		 * chill temperatures to meet or exceed local Wind Chill Warning
		 * criteria in the next 12 to 48 hours. Winter Storm Watch WS A
		 * Conditions are favorable for a winter storm event (Heavy Sleet, Heavy
		 * Snow, Ice Storm, Heavy Snow and Blowing Snow or a combination of
		 * events) to meet or exceed local Winter Storm Warning criteria in the
		 * next 12 to 48 hours. 1 Watch added in late spring 2009 2. Long
		 * Duration Warnings. Long duration warnings provide our users and
		 * partners advance notice of hazardous weather events that threaten
		 * life or property. The following table contains a complete list of NWS
		 * warnings contained in the NDFD hazard grids: Warning Name VTEC pp s
		 * Warning Definition Blizzard Warning BZ W Sustained wind or frequent
		 * gusts greater than or equal to 35 mph accompanied by falling and/or
		 * blowing snow, frequently reducing visibility to less than 1/4 mile
		 * for three hours or more. Coastal Flood Warning CF W Inundation of
		 * land areas caused by sea waters over and above normal tidal action.
		 * Coastal flooding may impact the immediate oceanfront, gulfs, bays,
		 * back bays, sounds, and tidal portions of river mouths and inland
		 * tidal waterways. Dust Storm Warning DS W Widespread or localized
		 * blowing dust reducing Visibilities to 1/4 mile or less. Sustained
		 * winds of 25 mph or greater are usually required. Excessive Heat
		 * Warning EH W Heat Index values forecast to meet or exceed locally
		 * defined warning criteria for at least two days (Typical value: 1)
		 * Maximum daytime HI >105°F north to110°F south 2) Minimum nighttime
		 * lows >75°F). A-4 Extreme Cold Warning EC W Air temperatures exceeding
		 * locally defined criteria for a prolonged period of time. Typical
		 * criteria: shelter temperatures -50°F or colder and air temperatures
		 * remaining below -40°F up to the 700-mb level for at least three days.
		 * Freeze Warning FZ W Minimum shelter temperature is forecast to be
		 * 32°F or less during the locally defined growing season. Gale Warning1
		 * GL W A warning of sustained surface winds, or frequent gusts, in the
		 * range of 34 knots (39 mph) to 47 knots (54 mph) inclusive, either
		 * predicted or occurring, and not directly associated with a tropical
		 * cyclone. Hard Freeze Warning HZ W Minimum shelter temperature is
		 * forecast to be 26°F or less during the locally defined growing
		 * season. Hazardous Seas Warning1 SE W Wave heights and/or wave
		 * steepness values meeting or exceeding locally defined warning
		 * criteria. Heavy Freezing Spray Warning1 UP W An accumulation of
		 * freezing water droplets on a vessel at a rate of 2 cm per hour or
		 * greater caused by some appropriate combination of cold water, wind,
		 * cold air temperature, and vessel movement. Heavy Snow Warning3 HS W
		 * Snow accumulation meeting or exceeding locally defined 12 and/or 24
		 * hour warning criteria. High Surf Warning SU W Warning for breaking
		 * wave action resulting in an especially heightened threat to life and
		 * property within the surf zone. High Wind Warning HW W Wind speeds
		 * forecast to meet or exceed locally defined warning criteria. (Typical
		 * values are sustained wind speeds of 40 mph or greater lasting for 1
		 * hour or longer, or winds of 58 mph or greater for any duration).
		 * Hurricane Force Wind Warning1 HF W Sustained winds, or frequent
		 * gusts, of 64 knots (74 mph) or greater, either predicted or
		 * occurring, and not directly associated with a tropical cyclone.
		 * Hurricane Warning HU W Sustained surface winds of 64 knots (74 mph)
		 * or higher associated with a hurricane are expected in a specified
		 * coastal area within 24 hours. Hurricane Wind Warning HI W Tropical
		 * cyclone to spread hurricane force sustained winds of 64 knots (74
		 * mph) or greater inland within 24 hours. Ice Storm Warning IS W Ice
		 * accumulation meeting or exceeding locally defined warning criteria
		 * (typical value is 1/4 inch or more). Lake Effect Snow Warning LE W
		 * Widespread or localized lake induced snow squalls or heavy showers
		 * which produce snowfall accumulation meeting or exceeding locally
		 * defined warning criteria. A-5 Lakeshore Flood Warning LS W Inundation
		 * of land areas adjacent to one of the Great Lakes caused by lake water
		 * exceeding normal levels. Lakeshore flooding impacts the immediate
		 * lakefront, bays, and the interfaces of lakes and connecting
		 * waterways, such as rivers. Red Flag Warning FW W Warning to alert
		 * area firefighting and land management agencies of the onset of
		 * critical weather and fuel moisture conditions ideal for rapid or
		 * dramatic increases in wildfire activity. Sleet Warning3 IP W Sleet
		 * accumulation meeting or exceeding locally defined warning criteria
		 * (typical value is 1/2 inch or more). Storm Warning SR W A warning of
		 * sustained surface winds, or frequent gusts, in the range of 48 knots
		 * (55 mph) to 63 knots (73 mph) inclusive, either predicted or
		 * occurring, and not directly associated with a tropical cyclone.
		 * Tropical Storm Warning TR W Sustained surface winds, associated with
		 * a tropical cyclone of 34 to 63 knots (39 to 73 mph), expected in a
		 * specified coastal area within 24 hours. Tropical Storm Wind Warning
		 * TI W Tropical cyclone to spread tropical storm force sustained winds
		 * of 34 to 63 knots (39 to 73 mph) inland within 24 hours. Tsunami
		 * Warning2 TS W A warning for the imminent threat of a tsunami from a
		 * large undersea earthquake or following confirmation that a
		 * potentially destructive tsunami is underway. The highest level of
		 * tsunami alert. Typhoon Warning TY W Sustained surface winds of 64
		 * knots (74 mph) or higher associated with a typhoon are expected in
		 * specified coastal areas in the western Pacific ocean within 24 hours
		 * or less. Wind Chill Warning WC W Wind chill temperatures reaching or
		 * exceeding locally defined warning criteria (typical value is -18°F or
		 * colder). Winter Storm Warning WS W Winter weather event having more
		 * than one predominant hazard (i.e., heavy snow and blowing snow, snow
		 * and ice, snow and sleet, sleet and ice, or snow, sleet and ice)
		 * meeting or exceeding locally defined 12 and/or 24 hour warning
		 * criteria. 1 Warning not in the NDFD hazard grids issued by Alaska
		 * Region WFOs 2 Warning added in late spring 2009 3 Warning
		 * discontinued in winter 2008 A-6 3. Long Duration Advisories. Long
		 * duration advisories are for less serious conditions that cause
		 * significant inconvenience and, if caution is not exercised, could
		 * lead to situations that may threaten life and/or property. The
		 * following table contains a complete list of NWS advisories contained
		 * in the NDFD hazard grids: Advisory Name VTEC Pp s Advisory Definition
		 * Air Stagnation Advisory AS Y Atmospheric conditions stable enough to
		 * cause air pollutants to accumulate in a given area. Criteria
		 * developed in conjunction with the local or state EPA and the product
		 * issued at their request. Ashfall Advisory AF Y Conditions associated
		 * with airborne ash plume resulting in ongoing deposition at the
		 * surface. Ashfall may originate directly from a volcanic eruption, or
		 * indirectly by wind suspending the ash. Ashfall Advisory1 MH1 Y
		 * Conditions associated with airborne ash plume resulting in ongoing
		 * deposition over the coastal waters. Ashfall may originate directly
		 * from a volcanic eruption, or indirectly by wind suspending the ash.
		 * Blowing Dust Advisory DU Y Widespread or localized blowing dust
		 * reducing visibilities to one mile or less, but greater than 1/4 mile.
		 * Winds of 25 mph or greater are usually required. Blowing Snow
		 * Advisory2 BS Y Widespread or localized blowing snow reducing
		 * visibilities to ¼ mile or less with winds less than 35 mph. Brisk
		 * Wind Advisory BW Y Small Craft Advisory winds expected for
		 * ice-covered waters. Coastal Flood Advisory CF Y Minor flooding, such
		 * as minor tidal overflow, is occurring or is possible within 12 hours.
		 * Dense Fog Advisory FG Y Widespread or localized fog reducing
		 * visibilities to regionally or locally defined limitations not to
		 * exceed one ¼ mile over land and one nautical mile over water. Dense
		 * Fog Advisory1 MF1 Y Widespread or localized fog reducing visibilities
		 * to regionally or locally defined limitations not to exceed one
		 * nautical mile over water. Dense Smoke Advisory SM Y Widespread or
		 * localized smoke reducing visibilities to regionally or locally
		 * defined limitations not to exceed one ¼ mile over land and one
		 * nautical mile over water. Dense Smoke Advisory1 MS1 Widespread or
		 * localized smoke reducing visibilities to regionally or locally
		 * defined limitations not to exceed one nautical mile over water.
		 * Freezing Fog Advisory ZF Y Very light ice accumulation from freezing
		 * fog. Freezing Rain Advisory ZR Y Light ice accumulation (freezing
		 * rain and/or freezing drizzle) meeting or exceeding locally defined
		 * advisory criteria, but remaining below warning criteria. A-7 Freezing
		 * Spray Advisory UP ZY3 Y An accumulation of freezing water droplets on
		 * a vessel at a rate of less than 2 centimeters (cm) per hour caused by
		 * some appropriate combination of cold water, wind, cold air
		 * temperature, and vessel movement. Frost Advisory FR Y Minimum shelter
		 * temperature forecast to be 33 to 36°F during the locally defined
		 * growing season, on nights with good radiational cooling conditions
		 * (e.g., light winds and clear skies). Heat Advisory HT Y Heat Index
		 * values forecast to meet or exceed locally defined advisory criteria
		 * for one to two days (Typical value: 1) Maximum daytime HI >100°F
		 * north to105°F south 2) Minimum nighttime lows >75°F). High Surf
		 * Advisory SU Y Breaking wave action poses a threat to life and
		 * property within the surf zone. Lake Effect Snow Advisory LE Y
		 * Widespread or localized lake effect snowfall accumulation reaching or
		 * exceeding locally defined advisory criteria, but remaining below
		 * warning criteria. Lake Effect Snow and Blowing Snow Advisory2 LB Y
		 * Sustained wind or frequent gusts of 25 to 34 mph (or locally defined)
		 * accompanied by falling and blowing lake effect snow, occasionally
		 * reducing visibility to 1/4 mile or less for three hours or more. Lake
		 * Wind Advisory LW Y Sustained wind speeds of 20 to 29 mph (or locally
		 * defined) lasting for 1 hour or longer for inland lakes. Lakeshore
		 * Flood Advisory LS Y Minor flooding of land areas adjacent to one of
		 * the Great Lakes, such as minor tidal overflow, is occurring or is
		 * possible within 12 hours. Low Water Advisory LO Y Water levels are
		 * significantly below average and may cause impact to safe marine
		 * navigation. Sleet Advisory2 IP Y Sleet accumulation reaching or
		 * exceeding locally defined advisory criteria, but remaining below
		 * warning criteria. Small Craft Advisory SC Y Sustained wind speeds or
		 * frequent gusts of 20 to 33 knots (locally defined) and/or seas or
		 * waves 4 feet and greater (locally defined). Small Craft Advisory for
		 * Hazardous Seas SW Y Wind speeds are lower than small craft advisory
		 * criteria, yet waves or seas are potentially hazardous due to wave
		 * period, steepness, or swell direction. Small Craft Advisory for Rough
		 * Bar RB Y Waves in or near bars are hazardous to mariners due to the
		 * interaction of swell, tidal or river currents in relatively shallow
		 * water. Small Craft Advisory for Winds SI Y When wave heights are
		 * lower than Small Craft Advisory criteria, yet wind speeds are
		 * potentially hazardous. Snow Advisory2 SN Y Snowfall accumulation
		 * meeting or exceeding locally defined advisory criteria, but remaining
		 * below warning criteria. A-8 Snow and Blowing Snow Advisory2 SB Y
		 * Sustained wind or frequent gusts of 25 to 34 mph (or locally defined)
		 * accompanied by falling and blowing snow, occasionally reducing
		 * visibility to 1/4 mile or less for three hours or more. Tsunami
		 * Advisory4 TS Y Indicates that an area is either outside the current
		 * warning and watch regions or that the tsunami poses no danger to that
		 * area. The third highest level of tsunami alert. Wind Advisory WI Y
		 * Sustained wind speeds of 30 to 39 mph lasting for 1 hour or longer or
		 * locally defined. Wind Chill Advisory WC Y Wind chill temperatures
		 * reaching or exceeding locally defined advisory criteria, but
		 * remaining below warning criteria. Winter Weather Advisory WW Y Winter
		 * weather event having more than one predominant hazard (i.e., snow and
		 * ice, snow and sleet, or snow, ice and sleet) meeting or exceeding
		 * locally defined 12 and/or 24 hour advisory criteria for at least one
		 * of the precipitation elements, but remaining below warning criteria
		 */
	}
}
