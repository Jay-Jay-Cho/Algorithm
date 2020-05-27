package summer_winter_coding_2018;
import java.util.*;
public class SkillTree {
	
	static int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(int i=0;i<skill_trees.length;i++) {
        	String skill_tree = skill_trees[i];
        	StringBuilder sb = new StringBuilder();
        	for(int j=0;j<skill_tree.length();j++) {
        		Character c = skill_tree.charAt(j);
        		if(skill.contains(String.valueOf(c))) {
        			sb.append(c);
        		}
        	}
        	String target = sb.toString();
        	String org = skill.substring(0,target.length());
        	if(org.equals(target)) answer++;
        }
        
        return answer;
    }

	public static void main(String[] args) {
		String skill = "CBD";
		String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
		int ans = solution(skill,skill_trees);
		System.out.println(ans);
	}

}
