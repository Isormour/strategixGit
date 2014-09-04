package com.me.mygdxgame.Animation.Person;

import java.util.HashMap;
import java.util.Map;

import com.me.mygdxgame.Animation.AnimationAsset;
import com.me.mygdxgame.Animation.AnimationAssetInternalFile;

public class AnimationPersonAssets {
	private static AnimationPersonAssets instance = null;

	Map<String, AnimationAsset> assetVault = new HashMap<String, AnimationAsset>();
	
	protected AnimationPersonAssets(){
		assetVault.put("attack", new AnimationAssetInternalFile("data/attack.png", 10, 1, 64, 64, 2f));
		assetVault.put("greet", new AnimationAssetInternalFile("data/greet.png", 10, 1, 64, 64, 2f));
		assetVault.put("death", new AnimationAssetInternalFile("data/death.png", 10, 1, 64, 64, 2f));
		assetVault.put("walk", new AnimationAssetInternalFile("data/walk.png", 10, 2, 64, 64, 1.25f));
	}
	
	public static AnimationPersonAssets getInstance() {
		if (instance == null) instance = new AnimationPersonAssets();
		return instance;
	}
	
	public AnimationAsset getAnimationAsset(String name){
		return assetVault.get(name);
	}
}
