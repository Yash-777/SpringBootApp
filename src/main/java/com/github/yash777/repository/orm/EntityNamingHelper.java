package com.github.yash777.repository.orm;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

/**
 * <a href="https://docs.jboss.org/hibernate/orm/5.1/userguide/html_single/chapters/domain/naming.html">
 * docs.jboss.org/hibernate</a>
 * 
 * @author yashwanth.m
 *
 */
public class EntityNamingHelper {
	private static final Map<String,String> ABBREVIATIONS = buildAbbreviationMap();
	private static Map<String, String> buildAbbreviationMap() {
		TreeMap<String,String> abbreviationMap = new TreeMap<> ( String.CASE_INSENSITIVE_ORDER );
		abbreviationMap.put( "account", "acct" );
		abbreviationMap.put( "number", "num" );
		return abbreviationMap;
	}
	
	public static void main(String[] args) {
		String[] names = {"filepath", "filePath", "prjID", "userid", "created_date", "videotype"};
		namings(names);
	}
	public static void namings(String[] names) {
		for (int i = 0; i < names.length; i++) {
			System.out.println("=========================");
			LinkedList<String> helper = splitAndReplace( names[i] );
			
			for (String string : helper) {
				System.out.println( string );
			}
			
			/*JdbcEnvironment jdbcEnvironment
			jdbcEnvironment.getIdentifierHelper().toIdentifier(
					join( parts ),
					name.isQuoted()
			)*/
		}
	}
	
	private static LinkedList<String> splitAndReplace(String name) {
		LinkedList<String> result = new LinkedList<>();
		for ( String part : StringUtils.splitByCharacterTypeCamelCase( name ) ) {
			if ( part == null || part.trim().isEmpty() ) {
				// skip null and space
				continue;
			}
			part = applyAbbreviationReplacement( part );
			result.add( part.toLowerCase( Locale.ROOT ) );
		}
		return result;
	}
	
	private static String applyAbbreviationReplacement(String word) {
		if ( ABBREVIATIONS.containsKey( word ) ) {
			return ABBREVIATIONS.get( word );
		}

		return word;
	}
}
